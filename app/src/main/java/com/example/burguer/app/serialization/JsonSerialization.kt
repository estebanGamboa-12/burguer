package com.example.burguer.app.serialization

interface JsonSerialization {
    fun <T> toJson(obj: T): String
    fun <T> fromJson(json: String, type: Class<T>): T
}