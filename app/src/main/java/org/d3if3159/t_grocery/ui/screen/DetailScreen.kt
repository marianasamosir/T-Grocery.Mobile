package org.d3if3159.t_grocery.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3159.t_grocery.R
import org.d3if3159.t_grocery.ui.theme.TGroceryTheme
const val KEY_ID_PRODUK = "idProduk"

@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    var namaProduk by remember { mutableStateOf("") }
    var deskripsiProduk by remember { mutableStateOf("") }
    var hargaProduk by remember { mutableStateOf("") }
    var stokProduk by remember { mutableStateOf("") }

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
                    IconButton(onClick = { navController.popBackStack() }) {
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
            title = namaProduk,
            onTitleChange = { namaProduk = it },
            deskripsi = deskripsiProduk,
            onDeskripsiChange = { deskripsiProduk = it },
            harga = hargaProduk,
            onHargaChange = { hargaProduk = it },
            stok = stokProduk,
            onStokChange = { stokProduk = it },
            modifier= Modifier.padding(padding)
        )
    }
}

@Composable
fun FormTambahProduk(
    title: String, onTitleChange: (String) -> Unit,
    deskripsi: String, onDeskripsiChange: (String) -> Unit,
    harga: String, onHargaChange: (String) -> Unit,
    stok: String, onStokChange: (String) -> Unit,
    modifier: Modifier,
    id: Long? = null
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
                    color = Color(0xFFACACAC)
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