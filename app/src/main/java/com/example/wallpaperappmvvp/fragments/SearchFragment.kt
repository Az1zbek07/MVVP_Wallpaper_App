package com.example.wallpaperappmvvp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperappmvvp.MainActivity
import com.example.wallpaperappmvvp.R
import com.example.wallpaperappmvvp.adapter.PhotoAdapter
import com.example.wallpaperappmvvp.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { (activity as MainActivity).viewModel }
    private val photoAdapter by lazy { PhotoAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        binding.rv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = photoAdapter
        }

        binding.btnSearch.setOnClickListener {
            val text = binding.editText.text.toString().trim()
            if (text.isNotBlank()){
                binding.pr.isVisible = true
                binding.rv.isVisible = false

                viewModel.searchPhotos(text)

                viewModel.images.observe(viewLifecycleOwner){
                    binding.pr.isVisible = false
                    binding.rv.isVisible = true

                    photoAdapter.submitList(it.photos)
                }
            }else{
                Toast.makeText(requireContext(), "Please enter values", Toast.LENGTH_SHORT).show()
            }
        }

        photoAdapter.onClick = {
            val bundle = bundleOf("data" to it)
            findNavController().navigate(R.id.action_searchFragment_to_detailFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}