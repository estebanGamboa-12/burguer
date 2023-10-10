package com.example.burguer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.burguer.app.ErrorApp
import com.example.burguer.domain.Burguer
import com.example.burguer.domain.GetBurguerUseCase
import com.example.burguer.domain.SaveBurguerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainModelView (
    private val saveBurguerUseCase:SaveBurguerUseCase,
    private val getBurguerUseCase: GetBurguerUseCase
    ):ViewModel(){

        private val  _uiState= MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

        fun saveBurguer(name:String, minutes:String, porcentajeTop:String,porcentajeBottom:String ){
            saveBurguerUseCase(SaveBurguerUseCase.Input(name,minutes,porcentajeTop,porcentajeBottom)).fold(
                { responseError(it) },
                { responseSuccess(it) }
            )
        }

        fun loadBurguer(){
            viewModelScope.launch(Dispatchers.IO) {
                getBurguerUseCase().fold(
                    { responseError(it) },
                    { responseGetUserSuccess(it) }
                )
            }
        }
    //Errores
    private fun responseError(errorApp: ErrorApp) {

    }

    private fun responseSuccess(isOk: Boolean) {

    }

    private fun responseGetUserSuccess(burguer: Burguer) {
        _uiState.postValue(UiState(burguer = burguer))
    }
    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val burguer: Burguer? = null
    )
    }