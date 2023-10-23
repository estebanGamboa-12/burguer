package com.example.burguer.data.remote

import com.example.burguer.app.ErrorApp
import com.example.burguer.domain.Burguer
import com.example.burguer.app.Either
import com.example.burguer.app.left
import com.example.burguer.app.right
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiMockRemoteDataSource() {

    suspend fun getBurguer(): Either<ErrorApp, List<Burguer>> {

        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dam.sitehub.es/curso-2023-2024/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(ApiService::class.java)

            val response = service.getBurguers()

            if (response.isSuccessful) {
                val itemsList = response.body()
                if (itemsList != null && itemsList.isNotEmpty()) {
                    return itemsList.right()
                } else {
                    // Manejar el caso en el que la lista está vacía
                    return ErrorApp.DataError.left()
                }
            } else {
                // Manejar el caso en el que la respuesta no es exitosa
                return ErrorApp.NetworkError.left()
            }
        } catch (e: Exception) {
            // Manejar errores de red u otros errores
            return ErrorApp.NetworkError.left()
        }
    }
}
