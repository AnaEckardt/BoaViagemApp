package com.example.boaviagemapp.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.boaviagemapp.dao.DadosDao
import com.example.boaviagemapp.dao.DestinoDao
import com.example.boaviagemapp.models.Dados
import com.example.boaviagemapp.models.Destino


@Database(entities = [Dados::class, Destino::class], version = 1, exportSchema = false) //preciso fazer anotação @database, passar as tabelas do banco e a versão, não vai exportar as informação do bando de dados;
abstract class AppDataBase : RoomDatabase() { //classe abstrata que herda de roomdatabase

    abstract val dadosDao: DadosDao
    abstract val destinoDao: DestinoDao


    companion object { //static class, não guarda dados;
        @Volatile //garantir que essa instância seja executada só uma vez;
        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context : Context): AppDataBase = INSTANCE ?: synchronized(this) {  //synchronized: só permite executar um acesso ao banco por vez
            val instance = Room.databaseBuilder(  //se INSTANCE não existe, ele Vai depopis do ?: e cria a instância. Na próxima vez que executar ele já vai ter a instância.
                context,
                AppDataBase::class.java,
                "appdatabase-db" //num outro projeto, trocar apenas o nome do banco de dados.
            ).build()
            INSTANCE = instance
            instance
        }
    }
}

