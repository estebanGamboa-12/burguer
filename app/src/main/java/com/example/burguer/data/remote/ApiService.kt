package com.example.burguer.data.remote

import com.example.burguer.domain.Burguer
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("burguer-view.json")
    suspend fun getBurguers():Response<List<Burguer>>
}