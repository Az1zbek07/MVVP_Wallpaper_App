package com.example.wallpaperappmvvp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wallpaperappmvvp.MainActivity
import com.example.wallpaperappmvvp.R
import com.example.wallpaperappmvvp.adapter.MyUrlAdapter
import com.example.wallpaperappmvvp.database.MyUrl
import com.example.wallpaperappmvvp.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { (activity as MainActivity).viewModel }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        val myUrl = arguments?.getParcelable<MyUrl>("data")
        with(binding){
            Glide.with(imageView).load(myUrl?.url).into(imageView)
        }

        binding.btnLiked.setOnClickListener {
            viewModel.savePhoto(myUrl!!)
            Toast.makeText(requireContext(), "Successfully saved to favourite", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}