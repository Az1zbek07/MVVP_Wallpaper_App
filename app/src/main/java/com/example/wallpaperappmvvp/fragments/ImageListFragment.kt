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
import com.example.wallpaperappmvvp.adapter.PhotoAdapter
import com.example.wallpaperappmvvp.databinding.FragmentImageListBinding
import com.example.wallpaperappmvvp.viewmodel.FirstViewModel

class ImageListFragment : Fragment(R.layout.fragment_image_list) {
    private var _binding: FragmentImageListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { (activity as MainActivity).viewModel }
    private val photoAdapter by lazy { PhotoAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentImageListBinding.bind(view)

        checkViews(true)

        viewModel.getPhotosByApi()
        binding.rv.apply {
            checkViews(false)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = photoAdapter
        }
        viewModel.images.observe(viewLifecycleOwner){
            photoAdapter.submitList(it.photos)
        }

        binding.btnLiked.setOnClickListener {
            findNavController().navigate(R.id.action_imageListFragment_to_likedFragment)
        }
        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_imageListFragment_to_searchFragment)
        }
        photoAdapter.onClick = {
            val bundle = bundleOf("data" to it)
            findNavController().navigate(R.id.action_imageListFragment_to_detailFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun checkViews(boolean: Boolean){
        binding.pr.isVisible = boolean
        binding.rv.isVisible = !boolean
        binding.btnLiked.isVisible = !boolean
        binding.btnSearch.isVisible = !boolean
    }
}