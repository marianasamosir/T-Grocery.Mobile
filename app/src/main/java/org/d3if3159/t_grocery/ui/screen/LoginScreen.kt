package org.d3if3159.t_grocery.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun LoginScreen(navController: NavHostController) {
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
        LoginContent(Modifier.padding(padding), navController)
    }
}

@Composable
fun LoginContent(modifier: Modifier, navController: NavHostController) {

    var username by rememberSaveable { mutableStateOf("") }
    var usernameError by rememberSaveable { mutableStateOf(false) }

    var kataSandi by rememberSaveable { mutableStateOf("") }
    var kataSandiError by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.selamat_datang),
            style = TextStyle(
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(id = R.string.keterangan_login_1),
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = {
                Text(
                    text = stringResource(id = R.string.username),
                    modifier = Modifier.padding(start = 16.dp)
                )
            },
            isError = usernameError,
            trailingIcon = { IconPickerLogin(usernameError, "") },
            supportingText = { ErrorHintLogin(usernameError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 43.dp)
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
            isError = kataSandiError,
            trailingIcon = { IconPickerLogin(kataSandiError, "")},
            supportingText = { ErrorHintLogin(kataSandiError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(55.dp)
        )
        Text(
            text = stringResource(id = R.string.keterangan_login_2),
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.padding(top = 60.dp)
        )
        Button(
            onClick = {
                // Validasi input username dan kata sandi
                usernameError = (username.isBlank() || username == "0")
                kataSandiError = (kataSandi.isBlank() || kataSandi == "0")

                // Cek apakah ada error
                if (!usernameError && !kataSandiError) {
                    // Jika tidak ada error, lakukan navigasi
                    navController.navigate(Screen.Homepage.route)
                }

            },
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB11116))
        )
        {
            Text(
                text = stringResource(id = R.string.btn_masuk),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
        Text(
            text = stringResource(id = R.string.keterangan_login_3),
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

@Composable
fun IconPickerLogin (isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHintLogin(isError: Boolean) {
    if (isError){
        Text(text = stringResource(id = R.string.input_invalid))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun LoginScreenPreview() {
    TGroceryTheme {
        LoginScreen(rememberNavController())
    }
}