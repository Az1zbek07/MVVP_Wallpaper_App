package com.example.wallpaperappmvvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.wallpaperappmvvp.databinding.ActivityMainBinding
import com.example.wallpaperappmvvp.viewmodel.FirstViewModel
import com.example.wallpaperappmvvp.viewmodel.FirstViewModelFactory

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var viewModel: FirstViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, FirstViewModelFactory(application))[FirstViewModel::class.java]
    }
}