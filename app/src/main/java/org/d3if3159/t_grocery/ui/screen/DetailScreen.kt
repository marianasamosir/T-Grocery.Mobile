package org.d3if3159.t_grocery.ui.screen

import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import org.d3if3159.t_grocery.R
import org.d3if3159.t_grocery.database.BarangDb
import org.d3if3159.t_grocery.ui.theme.TGroceryTheme
import org.d3if3159.t_grocery.util.ViewModelFactory

const val KEY_ID_PRODUK = "idProduk"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val db = BarangDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel : DetailViewModel = viewModel(factory = factory)

    var namaProduk by remember { mutableStateOf("") }
    var deskripsiProduk by remember { mutableStateOf("") }
    var hargaProduk by remember { mutableStateOf("") }
    var stokProduk by remember { mutableStateOf("") }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> imageUri = uri}

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getBarang(id) ?: return@LaunchedEffect
        imageUri = if (data.gambar.isEmpty()) Uri.parse(data.gambar) else null
        namaProduk = data.nama
        deskripsiProduk = data.deskripsi
        hargaProduk = data.harga
        stokProduk = data.stok
    }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo_tgrocery),
                        contentDescription = stringResource(id = R.string.logo),
                        modifier = Modifier
                            .size(120.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = Color(0xFFB11116)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (
                            namaProduk == "" ||
                            deskripsiProduk == "" ||
                            hargaProduk == "" ||
                            stokProduk == ""
                        ) {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if (id == null) {
                            viewModel.insert(imageUri.toString(), namaProduk, deskripsiProduk, hargaProduk, stokProduk)
                        } else {
                            viewModel.update(id, imageUri.toString(), namaProduk, deskripsiProduk, hargaProduk, stokProduk)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = Color(0xFFB11116)
                        )
                    }
                }
            )
        }
    ){padding ->
        FormTambahProduk(
            id = id,
            title = namaProduk,
            onTitleChange = { namaProduk = it },
            deskripsi = deskripsiProduk,
            onDeskripsiChange = { deskripsiProduk = it },
            harga = hargaProduk,
            onHargaChange = { hargaProduk = it },
            stok = stokProduk,
            onStokChange = { stokProduk = it },
            imageUri = imageUri,
            onImageClick = { launcher.launch("image/*") },
            modifier= Modifier.padding(padding)
        )
    }
}

@Composable
fun FormTambahProduk(
    id: Long? = null,
    title: String, onTitleChange: (String) -> Unit,
    deskripsi: String, onDeskripsiChange: (String) -> Unit,
    harga: String, onHargaChange: (String) -> Unit,
    stok: String, onStokChange: (String) -> Unit,
    imageUri: Uri?, onImageClick: () -> Unit,
    modifier: Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFDBF9BD))
            .height(50.dp)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (id == null)
            Text(
                text = stringResource(id = R.string.tambah_produk),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        else
            Text(
                text = stringResource(id = R.string.edit_produk),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 70.dp)
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = stringResource(id = R.string.detail_produk),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 15.dp)
        )
        imageUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .width(142.dp)
                    .height(216.dp),
                contentScale = ContentScale.FillBounds
            )
        }
        
        Button(
            onClick = { /*TODO*/ },
            border = BorderStroke(width = 1.dp, color = Color.Gray),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(size = 20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_image_24),
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.upload_foto),
                    color = Color.Gray
                )
            }
        }

        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.nama_produk),
                    fontSize = 13.sp,
                    color = Color(0xFFACACAC)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        )
        OutlinedTextField(
            value = deskripsi,
            onValueChange = { onDeskripsiChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.deskripsi_produk),
                    fontSize = 13.sp,
                    color = Color(0xFFACACAC)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        )
        OutlinedTextField(
            value = harga,
            onValueChange = { onHargaChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.harga_produk),
                    fontSize = 13.sp,
                    color = Color(0xFFACACAA)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        )
        OutlinedTextField(
            value = stok,
            onValueChange = { onStokChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.stok_produk),
                    fontSize = 13.sp,
                    color = Color(0xFFACACAC)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(50.dp),
//            colors = Color(0xFFACACAC),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    TGroceryTheme {
        DetailScreen(rememberNavController())
    }
}