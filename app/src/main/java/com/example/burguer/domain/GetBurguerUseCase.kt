package com.example.burguer.domain

import com.example.burguer.app.ErrorApp
import com.example.burguer.app.Either

class GetBurguerUseCase(private val repository: BurguerRepository) {

    operator suspend fun invoke(): Either<ErrorApp, Burguer> {
        return  repository.obtain()
    }
}