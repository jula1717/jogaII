package com.example.jogaii.ui.asanas

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jogaii.R
import com.example.jogaii.databinding.FragmentAsanasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AsanaFragment : Fragment(R.layout.fragment_asanas) {
    private val viewModel : AsanasViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAsanasBinding.bind(view)
        val asanaAdapter = AsanasAdapter()
        binding.apply {
            asanasRecyclerview.apply {
                adapter = asanaAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        viewModel.asanas.observe(viewLifecycleOwner){
            asanaAdapter.submitList(it)
        }
    }
}