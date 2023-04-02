package com.example.wallpaperappmvvp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperappmvvp.MainActivity
import com.example.wallpaperappmvvp.R
import com.example.wallpaperappmvvp.adapter.MyUrlAdapter
import com.example.wallpaperappmvvp.databinding.FragmentLikedBinding

class LikedFragment : Fragment(R.layout.fragment_liked) {
    private var _binding: FragmentLikedBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { (activity as MainActivity).viewModel }
    private val myUrlAdapter by lazy { MyUrlAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLikedBinding.bind(view)

        val response = viewModel.getPhotosByDatabase()
        binding.pr.isVisible = true
        binding.rv.isVisible = false

        binding.rv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = myUrlAdapter
        }

        response.observe(viewLifecycleOwner){
            binding.pr.isVisible = false
            binding.rv.isVisible = true
            myUrlAdapter.submitList(it)
        }

        myUrlAdapter.onClick = {
            val bundle = bundleOf("data" to it)
            findNavController().navigate(R.id.action_likedFragment_to_detailFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}