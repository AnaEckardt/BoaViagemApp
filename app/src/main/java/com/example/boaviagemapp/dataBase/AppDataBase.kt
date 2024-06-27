package com.example.boaviagemapp.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.boaviagemapp.dao.DadosDao
import com.example.boaviagemapp.models.Dados


@Database(entities = [Dados::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract val dadosDao: DadosDao

    //companion é para instanciar apenas 1 vez o banco nao fazer varios acessos
    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context : Context): AppDataBase = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "appdatabase-db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}

