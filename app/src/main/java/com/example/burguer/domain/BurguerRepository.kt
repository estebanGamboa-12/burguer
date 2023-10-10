package com.example.burguer.domain

import com.example.burguer.app.ErrorApp
import com.iesam.kotlintrainning.Either

interface BurguerRepository {

    fun save(input: SaveBurguerUseCase.Input):Either<ErrorApp,Boolean>

    fun obtain():Either<ErrorApp,Burguer>
}