package org.d3if3159.t_grocery.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
}