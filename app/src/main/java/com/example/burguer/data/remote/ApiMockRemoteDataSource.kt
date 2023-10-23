package com.example.burguer.data.remote

import com.example.burguer.app.ErrorApp
import com.example.burguer.domain.Burguer
import com.example.burguer.app.Either
import com.example.burguer.app.left
import com.example.burguer.app.right
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class ApiMockRemoteDataSource() {

    suspend fun getBurguer(): Either<ErrorApp, Burguer> {


            val retrofit = Retrofit.Builder()
                .baseUrl("https://dam.sitehub.es/curso-2023-2024/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service:ApiService = retrofit.create(ApiService::class.java)


        try {
            val repos: Response<BurguerApiModel> = service.getBurguers()

            if (repos.isSuccessful) {
                return repos.body()!!.toModel().right()
            } else {
                return ErrorApp.NetworkError.left()
            }
        } catch (ex: TimeoutException) {
            return ErrorApp.NetworkError.left()
        } catch (ex: UnknownHostException) {
            return ErrorApp.NetworkError.left()
        } catch (ex: Exception) {
            return ErrorApp.NetworkError.left()
        }
    }
}
