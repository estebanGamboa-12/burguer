package com.example.burguer.domain

import com.example.burguer.app.ErrorApp
import com.example.burguer.app.Either

interface BurguerRepository {


    suspend fun obtain(): Either<ErrorApp, Burguer>
}