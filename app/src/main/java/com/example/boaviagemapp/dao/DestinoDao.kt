package com.example.boaviagemapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.boaviagemapp.models.Destino
import kotlinx.coroutines.flow.Flow

@Dao //anotação para indicar que é uma classe DAO, e precisa ser uma interface;
interface DestinoDao {
    @Insert
    fun insert(destino: Destino) : Long

    @Update
    fun update(destino: Destino)

    @Upsert //insert ou update = depende se tiver id ou não
    suspend fun upsert(destino: Destino) : Long //suspend: //pode ser executado numa corrotina, fora da thread principal

    @Query("select * from destino p order by p.id")  //método Get precisa passar o select
    fun getAll() : Flow<List<Destino>> //flow monitora o banco e traz as alterações

    @Query("select * from destino p where p.id = :id")
    suspend fun findById(id : Long) : Destino? //interrogação poder ser que nao retorne o produto

    @Delete
    suspend fun delete (destino: Destino)
}
