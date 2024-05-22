package org.d3if3159.t_grocery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3159.t_grocery.ui.screen.DetailScreen
import org.d3if3159.t_grocery.ui.screen.HomePageScreen
import org.d3if3159.t_grocery.ui.screen.KEY_ID_PRODUK
import org.d3if3159.t_grocery.ui.screen.LoginScreen
import org.d3if3159.t_grocery.ui.screen.MainScreen
import org.d3if3159.t_grocery.ui.screen.RegistrationScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.Register.route) {
            RegistrationScreen(navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.Homepage.route) {
            HomePageScreen(navController)
        }
        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormEdit.route,
            arguments = listOf(navArgument(KEY_ID_PRODUK) { type = NavType.LongType })
        ) {navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_PRODUK)
            DetailScreen(navController, id)
        }
    }
}