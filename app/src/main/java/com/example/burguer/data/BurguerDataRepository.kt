package com.example.burguer.data

import com.example.burguer.app.ErrorApp
import com.example.burguer.data.local.XmlLocalDataSource
import com.example.burguer.domain.Burguer
import com.example.burguer.domain.BurguerRepository
import com.example.burguer.domain.SaveBurguerUseCase
import com.iesam.kotlintrainning.Either

class BurguerDataRepository(private val localDataSource: XmlLocalDataSource):BurguerRepository {

    override fun save(input: SaveBurguerUseCase.Input): Either<ErrorApp, Boolean> {
        return localDataSource.saveBurguer(input)
    }

    override fun obtain(): Either<ErrorApp, Burguer> {
        return localDataSource.findBurguer()
    }

}