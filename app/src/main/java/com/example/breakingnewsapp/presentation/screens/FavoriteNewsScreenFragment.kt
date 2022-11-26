package com.example.breakingnewsapp.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.breakingnewsapp.databinding.FragmentFavoriteNewsScreenBinding
import com.example.breakingnewsapp.presentation.adapters.SaveNewsAdapter
import com.example.breakingnewsapp.presentation.viewmodels.SaveNewsViewModel
import com.example.breakingnewsapp.presentation.viewmodels.SaveNewsViewModelFactory

class FavoriteNewsScreenFragment : Fragment() {
   private var _binding: FragmentFavoriteNewsScreenBinding? = null
    val binding: FragmentFavoriteNewsScreenBinding
        get() = _binding ?: throw RuntimeException("neill")
    private lateinit var adapter: SaveNewsAdapter

    val viewModel: SaveNewsViewModel by lazy {
        ViewModelProvider(
            this,
            SaveNewsViewModelFactory(requireActivity().application)
        )[SaveNewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteNewsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getListOfArticles()
    }

    private fun getListOfArticles(){
        viewModel.articles.observe(viewLifecycleOwner){
            adapter = SaveNewsAdapter()
            adapter.submitList(it)
            binding.rvFavorites.adapter = adapter

        }
    }
}