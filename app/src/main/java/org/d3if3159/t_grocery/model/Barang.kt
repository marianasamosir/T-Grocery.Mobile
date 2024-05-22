package org.d3if3159.t_grocery.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val gambar: String,
    val nama: String,
    val deskripsi: String,
    val harga: String,
    val stok: String
)
