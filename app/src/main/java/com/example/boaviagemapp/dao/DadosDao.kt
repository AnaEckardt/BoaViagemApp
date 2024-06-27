package com.example.boaviagemapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.boaviagemapp.models.Dados
import kotlinx.coroutines.flow.Flow



    @Dao
    interface DadosDao {
        @Insert
        fun insert(dados: Dados) : Long

        @Update
        fun update(dados: Dados)

        @Upsert //insere ou altera depende se recebe o id ou nao
        suspend fun upsert(dados: Dados) : Long //suspend diz que pode ser executado fora da tread principal

        @Query("select * from dados p order by p.id")
        fun getAll() : Flow<List<Dados>> //flow monitora o banco e traz as alterações

        @Query("select * from dados p where p.id = :id")
        fun findById(id : Long) : Dados? //interrogação poder ser que nao retorne o produto

        @Delete
        fun delete (dados: Dados)
    }
