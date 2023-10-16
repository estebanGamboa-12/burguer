package com.example.burguer.domain

import com.example.burguer.app.ErrorApp
import com.iesam.kotlintrainning.Either

interface BurguerRepository {

    fun save( name:String,minutes:String,percentTop:String,percentBottom:String):Either<ErrorApp,Boolean>

    fun obtain():Either<ErrorApp,Burguer>
}