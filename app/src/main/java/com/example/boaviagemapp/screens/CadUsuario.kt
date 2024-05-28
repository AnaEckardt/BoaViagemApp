package com.example.boaviagemapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boaviagemapp.viewmodels.DadosViewModel

@Composable
fun cadUsuario(
    onBack: () -> Unit,
    dadosViewModel: DadosViewModel = viewModel()
) {//retorno do botao para voltar a main

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val loginState = dadosViewModel.uiState.collectAsState()
        val passState = dadosViewModel.uiState.collectAsState()
        val emailState = dadosViewModel.uiState.collectAsState()

        Row {
            Text(
                text = "Cadastro de Usuário",
                textAlign = TextAlign.Center,
                fontSize = 26.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
            )
        }
        Row {
            Text(
                text = "Login",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 25.dp)
                    .fillMaxWidth()
            )
        }

        Row(
        ) {
            OutlinedTextField(
                value = loginState.value.login,
                onValueChange = {dadosViewModel.updateLogin(it)},
                modifier = Modifier
                    .padding(start = 55.dp, top = 10.dp)
            )
        }
        Row {
            Text(
                text = "Senha",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
            )
        }

        Row(
        ) {
            OutlinedTextField(
                value = passState.value.senha,
                onValueChange = {dadosViewModel.updateSenha(it)},
                modifier = Modifier
                    .padding(start = 55.dp, top = 10.dp)
            )
        }
        Row {
            Text(
                text = "Email",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
            )
        }
        Row(
        ) {
            OutlinedTextField(
                value = emailState.value.email,
                onValueChange = {dadosViewModel.updateEmail(it)},
                modifier = Modifier
                    .padding(start = 55.dp, top = 10.dp)
            )
        }

        Row {
            Button(
                onClick = { onBack() },
                modifier = Modifier
                    .padding(start = 127.dp, top = 25.dp)

            ) {
                Text(
                    text = "Cadastrar",
                    fontSize = 22.sp
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewAbout() {
    cadUsuario({})
}