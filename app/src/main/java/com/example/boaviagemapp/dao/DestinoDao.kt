package com.example.boaviagemapp.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.boaviagemapp.models.Destino
import kotlinx.coroutines.flow.Flow

interface DestinoDao {
    @Insert
    fun insert(destino: Destino) : Long

    @Update
    fun update(destino: Destino)

    @Upsert //insere ou altera depende se recebe o id ou nao
    suspend fun upsert(destino: Destino) : Long //suspend diz que pode ser executado fora da tread principal

    @Query("select * from destino p order by p.id")
    fun getAll() : Flow<List<Destino>> //flow monitora o banco e traz as alterações

    @Query("select * from destino p where p.id = :id")
    fun findById(id : Long) : Destino? //interrogação poder ser que nao retorne o produto

    @Delete
    fun delete (destino: Destino)
}
