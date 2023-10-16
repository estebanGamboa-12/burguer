package com.example.burguer.data

import com.example.burguer.app.ErrorApp
import com.example.burguer.data.local.XmlLocalDataSource
import com.example.burguer.data.remote.ApiMockRemoteDataSource
import com.example.burguer.domain.Burguer
import com.example.burguer.domain.BurguerRepository
import com.example.burguer.domain.SaveBurguerUseCase
import com.iesam.kotlintrainning.Either

class BurguerDataRepository(
    private val localDataSource: XmlLocalDataSource,
    private val apiMockRemoteDataSource: ApiMockRemoteDataSource
):BurguerRepository {

    override fun save(name:String,  minutes:String, percentTop:String, percentBottom:String): Either<ErrorApp, Boolean> {



        return localDataSource.saveBurguer(name,minutes,percentTop,percentBottom)
    }

    override fun obtain(): Either<ErrorApp, Burguer> {
        return apiMockRemoteDataSource.getBurguer()
    }

}