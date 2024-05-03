package org.d3if3159.t_grocery.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3159.t_grocery.R
import org.d3if3159.t_grocery.navigation.Screen
import org.d3if3159.t_grocery.ui.theme.TGroceryTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
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
                title = {},
            )
        }
    ){padding ->
        ScreenContent(Modifier.padding(padding), navController)
    }
}

@Composable
fun ScreenContent(modifier: Modifier, navController: NavHostController) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 45.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.landing_page),
            contentDescription = "Ini gambar landing page",

        )
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = stringResource(id = R.string.selamat_datang),
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 43.dp)
            )
            Text(
                text = stringResource(id = R.string.deskripsi),
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(top = 43.dp)
                    .height(45.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB11116))
            )
            {
                Text(
                    text = stringResource(id = R.string.btn_masuk),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
            Button(
                onClick = { navController.navigate(Screen.Register.route) },
                modifier = Modifier.padding(top = 10.dp).height(45.dp).fillMaxWidth(),
                border = BorderStroke(width = 1.dp, color = Color(0xFFB11116)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
            )
            {
                Text(
                    text = stringResource(id = R.string.btn_daftar),
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    color = Color(0xFFB11116)
                )
            }
            Text(
                text = stringResource(id = R.string.persetujuan),
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(top = 26.dp),
            )
        }

    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    TGroceryTheme {
        MainScreen(rememberNavController())
    }
}