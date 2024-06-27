package com.example.boaviagemapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dados(

    @PrimaryKey (autoGenerate = true) val id: Long = 0,
    val login: String = "",
    val senha: String = "",
    val email : String = "",
    val visivel : Boolean = false
)