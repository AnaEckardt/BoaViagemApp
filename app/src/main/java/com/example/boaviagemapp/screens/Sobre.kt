package com.example.boaviagemapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Sobre(){
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ){
        Row{
            Text(
                textAlign = TextAlign.Center,
                text = "Desenvolvido e Criado Por",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row{
            Text(
                textAlign = TextAlign.Center,
                text = "ANA PAULA ECKARDT",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row{
            Text(
                textAlign = TextAlign.Center,
                text = "Atividade de Desenvolvimento Mobile",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row{
            Text(
                textAlign = TextAlign.Center,
                text = "E-mail para contratação",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row{
            Text(
                textAlign = TextAlign.Center,
                text = "ana.eckardt@gmail.com",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
