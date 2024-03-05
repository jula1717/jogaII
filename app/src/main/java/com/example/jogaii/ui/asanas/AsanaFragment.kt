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
                viewModel.sortOrder.value=SortOrder.BY_NAME
                true
            }
            R.id.action_sort_by_sanskrit->{
                viewModel.sortOrder.value=SortOrder.BY_SANSKRIT
                true
            }
            R.id.action_sort_by_difficulty->{
                viewModel.sortOrder.value=SortOrder.BY_DIFFICULTY
                true
            }
            R.id.action_sort_by_completed->{
                viewModel.sortOrder.value=SortOrder.BY_COMPLETED
                true
            }
            R.id.action_sort_by_uncompleted->{
                viewModel.sortOrder.value=SortOrder.BY_UNCOMPLETED
                true
            }
            R.id.action_sort_by_type->{
                viewModel.sortOrder.value=SortOrder.BY_TYPE
                true
            }
            R.id.action_hide_completed_tasks->{
                item.isChecked=!item.isChecked
                viewModel.hideCompleted.value=item.isChecked
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}