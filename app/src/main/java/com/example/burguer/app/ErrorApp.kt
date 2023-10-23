package com.example.burguer.app

sealed class ErrorApp{
    object DataError : ErrorApp()
    object NetworkError : ErrorApp()
    object UnknowError : ErrorApp()
}
