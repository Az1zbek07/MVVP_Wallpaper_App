package com.example.wallpaperappmvvp.network

import com.example.wallpaperappmvvp.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("curated")
    suspend fun getPhotos(): Response<ImageResponse>

    @GET("search?query={text}")
    suspend fun searchPhotos(@Path("text") text: String): Response<ImageResponse>
}