package com.example.burguer.data.remote

import com.google.gson.annotations.SerializedName

data class BurguerApiModel (
    @SerializedName("title") val title: String,
    @SerializedName("discount") val discount: String,
    @SerializedName("rate") val rate: String,
    @SerializedName("eta") val eta: String,
    @SerializedName("url_image") val urlImage: String,
)