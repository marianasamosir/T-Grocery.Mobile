package org.d3if3159.t_grocery.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3159.t_grocery.database.BarangDao
import org.d3if3159.t_grocery.model.Barang

class DetailViewModel(private val dao: BarangDao) : ViewModel() {

    fun insert(gambar: String, nama: String, deskripsi: String, harga: String, stok: String) {
        val barang = Barang(
            gambar = gambar,
            nama = nama,
            deskripsi = deskripsi,
            harga = harga,
            stok = stok
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(barang)
        }
    }

    suspend fun getBarang(id: Long): Barang? {
        return dao.getBarangById(id)
    }

    fun update(id: Long, gambar: String, nama: String, deskripsi: String, harga: String, stok: String) {
        val barang = Barang(
            id = id,
            gambar = gambar,
            nama = nama,
            deskripsi = deskripsi,
            harga = harga,
            stok = stok
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(barang)
        }
    }
}