package com.example.breakingnewsapp.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.breakingnewsapp.databinding.FragmentArticleScreenBinding
import com.example.breakingnewsapp.presentation.viewmodels.SaveNewsViewModel
import com.example.breakingnewsapp.presentation.viewmodels.SaveNewsViewModelFactory
import com.google.android.material.snackbar.BaseTransientBottomBar
import java.time.Duration

class ArticleScreenFragment : Fragment() {
    private val args by navArgs<ArticleScreenFragmentArgs>()
    private var _binding: FragmentArticleScreenBinding? = null
    val binding: FragmentArticleScreenBinding
        get() = _binding ?: throw RuntimeException("binding is null")
    val viewModel: SaveNewsViewModel by lazy {
        ViewModelProvider(this,
        SaveNewsViewModelFactory(requireActivity().application)
            )[SaveNewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleScreenBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.webView.loadUrl(args.article.url!!)
        binding.fab.setOnClickListener {
            viewModel.saveNews(args.article)
            Toast.makeText(requireContext(),
                "Article has been saved",
                Toast.LENGTH_SHORT ).show()
        findNavController().popBackStack()
        }
    }



}