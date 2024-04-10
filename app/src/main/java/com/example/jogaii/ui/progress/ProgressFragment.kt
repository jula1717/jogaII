package com.example.jogaii.ui.progress

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jogaii.R
import com.example.jogaii.databinding.FragmentProgressBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgressFragment : Fragment (R.layout.fragment_progress) {
    private val viewModel: ProgressViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProgressBinding.bind(view)
        val progressAdapter = ProgressAdapter()
        binding.apply {
            progressRecyclerview.apply {
                adapter = progressAdapter
                layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
            }
        }
        viewModel.asanaTypesProgress.observe(viewLifecycleOwner){
            progressAdapter.submitList(it)
        }
    }
}