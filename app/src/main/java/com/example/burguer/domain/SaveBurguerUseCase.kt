package com.example.burguer.domain

import com.example.burguer.app.ErrorApp
import com.iesam.kotlintrainning.Either

class SaveBurguerUseCase (private val repository:BurguerRepository){

    operator  fun invoke( name:String,  minutes:String, percentTop:String, percentBottom:String):Either<ErrorApp,Boolean>{
        return  repository.save(name,minutes,percentTop,percentBottom)
    }


}