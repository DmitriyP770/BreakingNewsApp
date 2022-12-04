package com.example.breakingnewsapp.presentation.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.breakingnewsapp.core.util.Constants.SEARCH_DELAY
import com.example.breakingnewsapp.databinding.FragmentHomeScreenBinding
import com.example.breakingnewsapp.databinding.FragmentSearchNewsScreenBinding
import com.example.breakingnewsapp.presentation.adapters.NewsListAdapter
import com.example.breakingnewsapp.presentation.adapters.SearchNewsListAdapter
import com.example.breakingnewsapp.presentation.viewmodels.HomeViewModel
import com.example.breakingnewsapp.presentation.viewmodels.SearchNewsModelFactory
import com.example.breakingnewsapp.presentation.viewmodels.SearchNewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchNewsScreenFragment : Fragment() {

    private var _binding: FragmentSearchNewsScreenBinding? = null
    val binding: FragmentSearchNewsScreenBinding
        get() = _binding ?: throw RuntimeException("neill")
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            SearchNewsModelFactory(requireActivity().application)
        )[SearchNewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchNewsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var searchJob: Job? = null
        binding.etSearch.addTextChangedListener{
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(SEARCH_DELAY)
                if (it.toString().isNotEmpty())
                viewModel.searchNews(it.toString())
            }
        }

        lifecycleScope.launch {
            viewModel.searchState.collect{ state ->

                when(state){
                    is SearchNewsViewModel.SearchEvents.Loading -> {
                        binding.pgbSearch.isVisible = true
                    }
                    is SearchNewsViewModel.SearchEvents.Success -> {
                        binding.pgbSearch.isVisible = false
                        val adapter = SearchNewsListAdapter()
                        adapter.submitList(state.data)
                        binding.rvSearch.adapter = adapter
                    }
                    is SearchNewsViewModel.SearchEvents.Failure -> {
                        binding.pgbSearch.isVisible = false
                    }
                }

            }
        }

    }
}