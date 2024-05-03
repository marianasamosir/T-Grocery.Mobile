package org.d3if3159.t_grocery.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3159.t_grocery.R
import org.d3if3159.t_grocery.ui.theme.TGroceryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        IconButton(onClick = {navController.popBackStack()}) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.kembali),
                                tint = Color(0xFFB11116),

                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.logo_tgrocery),
                            contentDescription = stringResource(id = R.string.logo),
                            modifier = Modifier
                                .padding(start = 60.dp)
                                .size(120.dp)
                        )
                    }

                },
                title = {},
            )
        }
    ){padding ->
        RegistrationContent(Modifier.padding(padding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationContent(modifier: Modifier) {

    val marketList = listOf(
        stringResource(id = R.string.pilih_mart),
        stringResource(id = R.string.tjmart),
        stringResource(id = R.string.tmart_putra),
        stringResource(id = R.string.tmart_putri)
    )
    var market by rememberSaveable { mutableStateOf(false) }
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var kataSandi by rememberSaveable { mutableStateOf("") }
    var konfirmasiKataSandi by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 30.dp)
    ) {
        Text(
            text = stringResource(id = R.string.selamat_datang),
            style = TextStyle(
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.keterangan_regis_1),
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        ExposedDropdownMenuBox(
            expanded = market,
            onExpandedChange = { market = !market },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = marketList[selectedItemIndex],
                onValueChange = {},
                readOnly = true,
                trailingIcon = {},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .padding(top = 43.dp)
                    .height(50.dp)
            )
            ExposedDropdownMenu(
                expanded = market,
                onDismissRequest = { market = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                marketList.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                fontWeight = if (index == selectedItemIndex)
                                    FontWeight.Bold else null,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        },
                        onClick = {
                            selectedItemIndex = index
                            market = false
                        }
                    )
                }
            }
        }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = {
                Text(
                    text = stringResource(id = R.string.username),
                    modifier = Modifier.padding(start = 16.dp)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(55.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    text = stringResource(id = R.string.email),
                    modifier = Modifier.padding(start = 16.dp)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(55.dp)
        )
        OutlinedTextField(
            value = kataSandi,
            onValueChange = { kataSandi = it },
            label = {
                Text(
                    text = stringResource(id = R.string.kata_sandi),
                    modifier = Modifier.padding(start = 16.dp)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(55.dp)
        )
        OutlinedTextField(
            value = konfirmasiKataSandi,
            onValueChange = { konfirmasiKataSandi = it },
            label = {
                Text(
                    text = stringResource(id = R.string.konfirmasi_kata_sandi),
                    modifier = Modifier.padding(start = 16.dp)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(55.dp)
        )
        Button(
            onClick = { },
            modifier = Modifier
                .padding(top = 43.dp)
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB11116))
        )
        {
            Text(
                text = stringResource(id = R.string.daftar),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
        Text(
            text = stringResource(id = R.string.keterangan_regis_2),
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    TGroceryTheme {
        RegistrationScreen(rememberNavController())
    }
}