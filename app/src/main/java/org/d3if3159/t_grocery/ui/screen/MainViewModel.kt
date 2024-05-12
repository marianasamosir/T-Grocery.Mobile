package org.d3if3159.t_grocery.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3159.t_grocery.model.Barang

class MainViewModel: ViewModel() {
    val data = getDataDummy()

    private fun getDataDummy(): List<Barang> {
        val data = mutableListOf<Barang>()
        for (i in 19 downTo 10) {
            data.add(
                Barang(
                    i.toLong(),
                    "Queker",
                    "Quaker Original",
                    "Rp12.500",
                    "Strok: 5"
                )
            )
        }
        return data
    }
}