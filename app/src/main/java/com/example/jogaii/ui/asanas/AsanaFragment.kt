package com.example.jogaii.ui.asanas

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jogaii.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AsanaFragment : Fragment(R.layout.fragment_asanas) {
    private val viewModel : AsanasViewModel by viewModels()
}