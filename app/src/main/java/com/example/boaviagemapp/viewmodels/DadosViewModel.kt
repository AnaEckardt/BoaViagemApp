package com.example.boaviagemapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.boaviagemapp.dao.DadosDao
import com.example.boaviagemapp.dataBase.AppDataBase
import com.example.boaviagemapp.models.Dados
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DadosViewModelFactory(val db : AppDataBase) : ViewModelProvider.Factory{//tem que criar para usar o db
override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return DadosViewModel(db.dadosDao) as T
}
}
class DadosViewModel (val dadosDao: DadosDao): ViewModel(){

    private val _uiState = MutableStateFlow(Dados())
    val uiState : StateFlow<Dados> = _uiState.asStateFlow()

    fun updateLogin(newLogin : String){
        _uiState.update { it.copy(login = newLogin) }
    }

    fun updateSenha(newSenha : String){
        _uiState.update { it.copy(senha = newSenha) }
    }

    fun updadeVisivel (newVisivel : Boolean){
        _uiState.update { it.copy(visivel = newVisivel) }
    }

    fun updateEmail (newEmail : String){
        _uiState.update { it.copy(email = newEmail) }
    }

    private fun updateId (id : Long){
        _uiState.update {
            it.copy(id = id)
        }
    }

    fun save(){
        viewModelScope.launch { //cria um processo separado para nao travar o programa tudo que tem acesso ao banco
            val id = dadosDao.upsert(uiState.value) //insere ou altera se tiver
            if (id > 0){
                updateId(id)
            }
        }
    }
    fun saveNew() {
        save()
        new()
    }
    private fun new() {
        _uiState.update {
            it.copy(id = 0, login = "", senha = "", visivel = false ,email = "")
        }
    }


}