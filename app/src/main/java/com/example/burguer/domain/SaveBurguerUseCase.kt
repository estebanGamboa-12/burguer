package com.example.burguer.domain

import com.example.burguer.app.ErrorApp
import com.iesam.kotlintrainning.Either

class SaveBurguerUseCase (private val repository:BurguerRepository){

    operator  fun invoke(input: Input):Either<ErrorApp,Boolean>{
        return  repository.save(input)
    }

    data class Input(val name:String,val minutes:String,val percentTop:String,val percentBottom:String)
}