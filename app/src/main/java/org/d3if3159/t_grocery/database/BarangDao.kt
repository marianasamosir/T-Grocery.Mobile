package org.d3if3159.t_grocery.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3159.t_grocery.model.Barang

@Dao
interface BarangDao {

    @Insert
    suspend fun insert(barang: Barang)

    @Update
    suspend fun update(barang: Barang)

    @Query("SELECT * FROM barang ORDER BY stok ASC")
    fun getBarang(): Flow<List<Barang>>

    @Query("SELECT * FROM barang WHERE id = :id")
    suspend fun getBarangById(id: Long): Barang?

    @Query("DELETE FROM barang WHERE id = :id")
    suspend fun deleteById(id: Long)
}