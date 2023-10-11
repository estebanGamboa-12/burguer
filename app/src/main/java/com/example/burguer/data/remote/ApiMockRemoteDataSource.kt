package com.example.burguer.data.remote

import com.example.burguer.app.ErrorApp
import com.example.burguer.domain.Burguer
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.right

class ApiMockRemoteDataSource() {

    fun getBurguer():Either<ErrorApp,Burguer>{
        return Burguer(0,"Burguer It","22-30","-17","98").right()
    }
}