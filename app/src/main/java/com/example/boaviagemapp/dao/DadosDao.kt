package com.example.boaviagemapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.boaviagemapp.models.Dados
import kotlinx.coroutines.flow.Flow



    @Dao //anotação para indicar que é uma classe DAO, e precisa ser uma interface;
    interface DadosDao {
        @Insert
        fun insert(dados: Dados) : Long

        @Update
        fun update(dados: Dados)

        @Upsert //insere ou altera depende se recebe o id ou nao
        suspend fun upsert(dados: Dados) : Long //suspend: //pode ser executado numa corrotina, fora da thread principal
        @Query("select * from dados p order by p.id")
        fun getAll() : Flow<List<Dados>> //flow monitora o banco e trás as alterações (alteração de estado)

        @Query("select * from dados p where p.id = :id")
        suspend fun findById(id : Long) : Dados? //interrogação poder ser que nao retorne o produto
        @Query("select * from dados p where p.login = :login")
        suspend fun findByLogin(login : String) : Dados?
        @Delete
        suspend fun delete (dados: Dados)
    }
