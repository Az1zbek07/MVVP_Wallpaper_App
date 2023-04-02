package com.example.wallpaperappmvvp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperappmvvp.database.MyUrl
import com.example.wallpaperappmvvp.database.MyUrlDatabase
import com.example.wallpaperappmvvp.model.ImageResponse
import com.example.wallpaperappmvvp.network.RetrofitClient
import com.example.wallpaperappmvvp.repository.MyUrlRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class FirstViewModel(application: Application): ViewModel() {
    private val TAG = "@@@"
    private val apiService by lazy { RetrofitClient.provideRetrofit() }
    private val repository = MyUrlRepository(apiService, MyUrlDatabase.invoke(application).dao)

    private var _images: MutableLiveData<ImageResponse>? = MutableLiveData<ImageResponse>()
    val images: LiveData<ImageResponse> get() = _images!!

    fun getPhotosByApi(){
        _images = null
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repository.getPhotos()
                if (response.isSuccessful){
                    _images = MutableLiveData<ImageResponse>()
                    _images!!.postValue(response.body())
                }else{
                    Log.d(TAG, "getPhotosByApi: ${response.message()}")
                }
            } catch (e: Exception){
                Log.d(TAG, "getPhotosByApi: $e")
            }
        }
    }

    fun searchPhotos(text: String){
        _images = null
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repository.searchPhotos(text)
                if (response.isSuccessful){
                    _images = MutableLiveData<ImageResponse>()
                    _images!!.postValue(response.body())
                }else{
                    Log.d(TAG, "getPhotosByApi: ${response.message()}")
                }
            } catch (e: Exception){
                Log.d(TAG, "getPhotosByApi: $e")
            }
        }
    }

    fun getPhotosByDatabase() = repository.getAllUrls()

    fun savePhoto(myUrl: MyUrl){
        viewModelScope.launch(Dispatchers.IO){
            repository.saveMyUrl(myUrl)
        }
    }
}