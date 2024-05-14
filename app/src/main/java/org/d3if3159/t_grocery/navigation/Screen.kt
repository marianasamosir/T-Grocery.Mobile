package org.d3if3159.t_grocery.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object Register: Screen("registerScreen")
    data object Login: Screen("loginScreen")
    data object Homepage: Screen("homePage")
}