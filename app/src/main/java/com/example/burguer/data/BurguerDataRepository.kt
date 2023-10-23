package com.example.burguer.data

import XmlLocalDataSource
import com.example.burguer.app.ErrorApp
import com.example.burguer.data.remote.ApiMockRemoteDataSource
import com.example.burguer.domain.Burguer
import com.example.burguer.domain.BurguerRepository
import com.example.burguer.app.Either

 class BurguerDataRepository(
    private val localDataSource: XmlLocalDataSource,
    private val apiMockRemoteDataSource: ApiMockRemoteDataSource
):BurguerRepository {

    override suspend fun obtain(): Either<ErrorApp, Burguer> {
        var chat=localDataSource.getBurguers()
        chat.mapLeft {
            return apiMockRemoteDataSource.getBurguer().map {
                localDataSource.saveBurguers(it)
                it
            }
        }
        return apiMockRemoteDataSource.getBurguer().map {
            localDataSource.saveBurguers(it)
            it
        }
    }
    }

