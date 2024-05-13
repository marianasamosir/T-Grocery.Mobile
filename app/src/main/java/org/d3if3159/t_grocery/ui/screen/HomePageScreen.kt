package org.d3if3159.t_grocery.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3159.t_grocery.R
import org.d3if3159.t_grocery.model.Barang
import org.d3if3159.t_grocery.ui.theme.TGroceryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.logo_tgrocery),
                        contentDescription = stringResource(id = R.string.logo),
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(120.dp)
                    )
                },
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = stringResource(id = R.string.hi_username),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        }
    ){padding ->
        HomePageContent(Modifier.padding(padding))
    }
}

@Composable
fun HomePageContent(modifier: Modifier){

//    val viewModel: MainViewModel = viewModel()
    val context = LocalContext.current
    val data = emptyList<Barang>()

    Box(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.homepage),
            contentDescription = ""
        )
        Column(
            modifier = Modifier.padding(top = 36.dp, start = 40.dp, end = 150.dp)
        ) {
            Text(
                text = stringResource(id = R.string.desc_homepage_1),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.desc_homepage_2),
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = stringResource(id = R.string.desc_homepage_3),
                fontSize = 11.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
            .padding(top = 260.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(
            onClick = { /*TODO*/ },
            border = BorderStroke(1.dp, Color(0xFFB11116)),
            modifier = Modifier
                .height(35.dp)
                .width(150.dp)
        ) {
            Text(
                text = "Keseluruhan",
                color = Color(0xFFB11116)
            )
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(35.dp)
                .width(110.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB11116))
        ) {
            Text(text = stringResource(id = R.string.tambah))
        }
    }
    LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp, vertical = 30.dp)
                .padding(top = 300.dp)
        ) {
            items(data) {
                ListItem(barang = it) {
                    val pesan = context.getString(R.string.x_diklik, it.nama)
                    Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
                }
            }
        }

// KODE KALO MENANGANGI KEADAAN JIKA LIST KOSONG
    if(data.isEmpty()){
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.list_kosong))
        }

    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp, vertical = 30.dp)
                .padding(top = 300.dp)
        ) {
            items(data) {
                ListItem(barang = it) {
                    val pesan = context.getString(R.string.x_diklik, it.nama)
                    Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun ListItem(barang: Barang, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(2.dp)
            .border(BorderStroke(1.dp, Color(0xFFACACAC)), RoundedCornerShape(12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.produk_quaker),
            contentDescription = "",
            modifier = Modifier
                .padding(13.dp)
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxSize()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick },
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Column {
                Text(
                    text = barang.nama,
                    maxLines = 1,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = barang.deskripsi,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp
                )
                Text(
                    text = barang.harga,
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier.padding(start = 60.dp, top = 32.dp)
//                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = barang.stok,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HomePageScreenPreview() {
    TGroceryTheme {
        HomePageScreen(rememberNavController())
    }
}