package org.d3if3159.t_grocery.navigation

import org.d3if3159.t_grocery.ui.screen.KEY_ID_PRODUK

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object Register: Screen("registerScreen")
    data object Login: Screen("loginScreen")
    data object Homepage: Screen("homeageScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormEdit: Screen("detailScreen/{$KEY_ID_PRODUK}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}