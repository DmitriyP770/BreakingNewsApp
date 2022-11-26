package com.example.breakingnewsapp.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingnewsapp.core.util.Resource
import com.example.breakingnewsapp.databinding.FragmentHomeScreenBinding
import com.example.breakingnewsapp.presentation.adapters.NewsListAdapter
import com.example.breakingnewsapp.presentation.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
//        get() = _binding ?: throw RuntimeException()
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var  newsAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsListAdapter()
        lifecycleScope.launch {
            viewModel.modelState.collect{
                when(it){
                    is HomeViewModel.ModelEvents.Loading ->  {
                        binding.pgbHome.isVisible = true
                    }

                   is HomeViewModel.ModelEvents.Success -> {
                       binding.pgbHome.isVisible = false
                        newsAdapter.submitList(it.result)
                        binding.rvHome.adapter = newsAdapter

                    }

                    is HomeViewModel.ModelEvents.Failure ->{
                        binding.pgbHome.isVisible = false

                    }
                }
            }
        }
    }

}