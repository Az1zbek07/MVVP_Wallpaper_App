package com.example.wallpaperappmvvp.repository

import com.example.wallpaperappmvvp.database.MyUrl
import com.example.wallpaperappmvvp.database.MyUrlDao
import com.example.wallpaperappmvvp.network.ApiService

class MyUrlRepository(
    private val apiService: ApiService,
    private val dao: MyUrlDao
) {
    suspend fun saveMyUrl(myUrl: MyUrl) = dao.saveMyUrl(myUrl)

    fun getAllUrls() = dao.getAllUrls()

    suspend fun getPhotos() = apiService.getPhotos()

    suspend fun searchPhotos(text: String) = apiService.searchPhotos(text)
}