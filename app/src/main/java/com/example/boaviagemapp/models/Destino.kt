package com.example.boaviagemapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Destino(

    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val destino: String = "",
    val inicio: String = "",
    val fim: String = "",
    val valor: Double = 0.00,
    val finalidade: String = ""
)