package com.example.boaviagemapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.boaviagemapp.dao.DestinoDao
import com.example.boaviagemapp.dataBase.AppDataBase
import com.example.boaviagemapp.models.Destino
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DestinoViewModelFactory(val db : AppDataBase) : ViewModelProvider.Factory{//tem que criar para usar o db
override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return DadosViewModel(db.dadosDao) as T
}
}
class DestinoViewModel (val destinoDao: DestinoDao): ViewModel(){

    private val _uiState = MutableStateFlow(Destino())
    val uiState : StateFlow<Destino> = _uiState.asStateFlow()

    fun updateDestino(newDestino : String){
        _uiState.update { it.copy(destino = newDestino) }
    }

    fun updateInicio(newInicio : String){
        _uiState.update { it.copy(inicio = newInicio) }
    }

    fun updadeFim (newFim : String){
        _uiState.update { it.copy(fim = newFim) }
    }

    fun updateValor (newValor : Double){
        _uiState.update { it.copy(valor = newValor) }
    }

    fun updateFinalidade (newFinalidade : String){
        _uiState.update { it.copy(finalidade = newFinalidade) }
    }

    private fun updateId (id : Long){
        _uiState.update {
            it.copy(id = id)
        }
    }

    fun save(){
        viewModelScope.launch { //cria um processo separado para nao travar o programa tudo que tem acesso ao banco
            val id = destinoDao.upsert(uiState.value) //insere ou altera se tiver
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
            it.copy(id = 0, destino = "", inicio = "", fim = "" , valor = 0.0, finalidade = "")
        }
    }
}
