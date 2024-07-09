package com.example.boaviagemapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boaviagemapp.R
import com.example.boaviagemapp.dataBase.AppDataBase
import com.example.boaviagemapp.viewmodels.DadosViewModel
import com.example.boaviagemapp.viewmodels.DadosViewModelFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
@Composable
fun telaLogin(
    onCadUsuario: ()->Unit,
    onLogin: (pass: String) ->Unit,

){
    val db = AppDataBase.getDatabase(LocalContext.current)
    val dadosViewModel: DadosViewModel = viewModel(
        factory = DadosViewModelFactory(db)
    )

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    var coroutineScope = rememberCoroutineScope()

    val focus = LocalFocusManager.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .padding(it)
        ) {

            val loginState = dadosViewModel.uiState.collectAsState()
            val passState = dadosViewModel.uiState.collectAsState()
            val visivelState = dadosViewModel.uiState.collectAsState()

            Image(
                painter = painterResource(id = R.drawable.viagem),
                contentDescription = "Camera",
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(350.dp)
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 15.dp)
            )
            Text(
                text = "Usuário",
                fontSize = 22.sp
            )
            OutlinedTextField(
                value = loginState.value.login,
                onValueChange = { dadosViewModel.updateLogin(it) },
                label = {
                    Text(text = "Login")
                },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Senha",
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(top = 15.dp)
            )

            OutlinedTextField(
                value = passState.value.senha,
                onValueChange = { dadosViewModel.updateSenha(it) },
                label = {
                    Text(text = "Password")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation =
                if (visivelState.value.visivel)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

                trailingIcon = {
                    IconButton(onClick = {
                        dadosViewModel.updadeVisivel(!visivelState.value.visivel)
                    }) {
                        if (visivelState.value.visivel)
                            Icon(
                                painterResource(id = R.drawable.visible), ""
                            )
                        else
                            Icon(
                                painterResource(id = R.drawable.novisible), ""
                            )
                    }
                },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    MainScope().launch {
                        val pass = dadosViewModel.findByLogin(
                            dadosViewModel.uiState.value.login,
                            dadosViewModel.uiState.value.senha
                        )
                        if (pass != null) {
                            onLogin(pass.toString())
                        } else {
                            coroutineScope.launch {
                                focus.clearFocus()
                                snackbarHostState.showSnackbar(
                                    message = "login ou Senha errados",
                                    withDismissAction = true
                                )
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = "Login",
                    fontSize = 22.sp
                )
            }
            Button(
                onClick = { onCadUsuario() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(text = "Registrar novo Usuário")
            }
        }
    }
}

//
fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()