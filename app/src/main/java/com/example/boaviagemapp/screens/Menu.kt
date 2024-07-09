package com.example.boaviagemapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.boaviagemapp.R
import com.example.boaviagemapp.componentes.MyTopBar
import com.example.boaviagemapp.dataBase.AppDataBase
import com.example.boaviagemapp.viewmodels.DadosViewModel
import com.example.boaviagemapp.viewmodels.DadosViewModelFactory

private fun isSelected(currentDestination: NavDestination?, route:String): Boolean {
    return currentDestination?.hierarchy?.any {it.route == route} == true
}


@Composable
fun Home(id: String) {
    Scaffold(
        topBar = {
            MyTopBar("App Boa Viagem") {System.exit(1)}
        }
    ) {
        val db = AppDataBase.getDatabase(LocalContext.current)
        val dadosViewModel: DadosViewModel = viewModel(
            factory = DadosViewModelFactory(db)
        )
        LaunchedEffect(id) {
            if (id != null) {
                val user = dadosViewModel.findById(id.toLong())
                user?.let { dadosViewModel.setUiState(it) }
            }
        }
        val state = dadosViewModel.uiState.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Row {
                Text(
                    text = "Bem vindo " + state.value.login,
                    fontSize = 22.sp
                )
            }
        }
    }
}


@Composable
fun Menu(id: String){

    val navController = rememberNavController()

    Scaffold (
        bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.value?.destination

            BottomNavigation {

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "home"),
                    onClick = { navController.navigate("home") },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = ""
                        )
                    }
                )

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "todasviagens"),
                    onClick = { navController.navigate("todasviagens") },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = ""
                        )
                    }
                )

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "sobre"),
                    onClick = { navController.navigate("sobre") },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = ""
                        )
                    }
                )
            }
        }
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ){
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {
                    Home(id)
                }
                composable("todasviagens"){
                    Destinos()
                }

                composable("sobre"){
                    Sobre()
                }


            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewMenu() {
    Menu("")
}