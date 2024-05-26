package org.d3if3159.t_grocery.ui.screen

import android.content.res.Configuration
import android.net.Uri
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import org.d3if3159.t_grocery.R
import org.d3if3159.t_grocery.database.BarangDb
import org.d3if3159.t_grocery.model.Barang
import org.d3if3159.t_grocery.navigation.Screen
import org.d3if3159.t_grocery.ui.theme.TGroceryTheme
import org.d3if3159.t_grocery.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageScreen(navController: NavHostController) {

    var showList by remember { mutableStateOf(true) }

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
        HomePageContent(showList, Modifier.padding(padding), navController)
    }
}

@Composable
fun HomePageContent(
    showList: Boolean,
    modifier: Modifier,
    navController: NavHostController,
//  searchData: (String) -> Unit
){

    val context = LocalContext.current
    val db = BarangDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    val filteredProducts = if (searchText.isEmpty()) {
        data
    } else {
        data.filter { it.doesMactchSearchQuery(searchText) }
    }

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


        Button(
            onClick = {
                navController.navigate(Screen.FormBaru.route)
            },
            modifier = Modifier
                .height(45.dp)
                .width(120.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB11116)),
            border = BorderStroke(1.dp, Color(0xFFB11116))
        ) {
            Text(text = stringResource(id = R.string.tambah))
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 30.dp)
            .padding(top = 300.dp)
//      contentPadding = PaddingValues(bottom = 84.dp) (GAK PERLU PAKE KARENA TAMBAH NYA DIATAS)
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
                .padding(horizontal = 40.dp)
                .padding(top = 182.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.list_empty),
                contentDescription = "list empty",
                modifier = Modifier.size(110.dp)
            )
            Text(
                text = stringResource(id = R.string.list_kosong1),
                fontSize = 13.sp,
                color = Color(0xFFACACAC),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.list_kosong2),
                fontSize = 10.sp,
                color = Color(0xFFACACAC),
                fontWeight = FontWeight.Bold
            )
        }

    } else {
        Column(modifier = modifier) {
            //searchbar
            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .width(170.dp)
                    .height(25.dp),
//                .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text(text = "Search")},
                leadingIcon = { Icon(
                    Icons.Filled.Search, contentDescription = "Search Icon",
                    tint = Color(0xFFB11116)
                ) }, // Icon pencarian
                textStyle = TextStyle(color = Color(0xFFB11116)), // Gaya teks search bar
                shape = MaterialTheme.shapes.medium // Bentuk search bar
            )

            if (showList) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 40.dp, vertical = 30.dp)
                        .padding(top = 300.dp)
                ) {
                    items(filteredProducts) { barang ->
                        ListItem(barang = barang) {
                            navController.navigate(Screen.FormEdit.withId(barang.id))
                        }
                    }
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
        val imageUri = Uri.parse(barang.gambar)
        Image(
            painter = rememberAsyncImagePainter(model = imageUri),
            contentDescription = null,
            modifier = Modifier
                .padding(13.dp)
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
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
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = barang.harga,
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier.padding(start = 65.dp, top = 32.dp)
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