package com.example.burguer.domain

import com.example.burguer.app.ErrorApp
import com.iesam.kotlintrainning.Either

class GetBurguerUseCase(private val repository: BurguerRepository) {

    operator fun invoke(): Either<ErrorApp,Burguer>{
        return  repository.obtain()
    }
}