package com.example.jogaii.ui.asanas

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jogaii.R
import com.example.jogaii.databinding.FragmentAsanasBinding
import com.example.jogaii.util.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AsanaFragment : Fragment(R.layout.fragment_asanas) {
    private val viewModel: AsanasViewModel by viewModels()
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
        viewModel.asanas.observe(viewLifecycleOwner) {
            asanaAdapter.submitList(it)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_asanas, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChanged{
            viewModel.searchQuery.value=it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when(item.itemId){
            R.id.action_show_progress->{
                true
            }
            R.id.action_sort_by_name->{
                true
            }
            R.id.action_sort_by_sanskrit->{
                true
            }
            R.id.action_sort_by_difficulty->{
                true
            }
            R.id.action_sort_by_completed->{
                true
            }
            R.id.action_sort_by_uncompleted->{
                true
            }
            R.id.action_sort_by_type->{
                true
            }
            R.id.action_hide_completed_tasks->{
                item.isChecked=!item.isChecked
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}