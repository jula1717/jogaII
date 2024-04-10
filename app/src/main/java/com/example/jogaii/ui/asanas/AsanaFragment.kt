package com.example.jogaii.ui.asanas

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jogaii.R
import com.example.jogaii.data.Asana
import com.example.jogaii.data.AsanaWithType
import com.example.jogaii.data.SortOrder
import com.example.jogaii.databinding.FragmentAsanasBinding
import com.example.jogaii.exhaustive
import com.example.jogaii.ui.asanas.AsanasAdapter.OnItemClickListener
import com.example.jogaii.util.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AsanaFragment : Fragment(R.layout.fragment_asanas), OnItemClickListener {
    private val viewModel: AsanasViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAsanasBinding.bind(view)
        val asanaAdapter = AsanasAdapter(this)
        binding.apply {
            asanasRecyclerview.apply {
                adapter = asanaAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                (activity as AppCompatActivity?)!!.setSupportActionBar(binding.myToolbar)
            }
        }
        viewModel.asanas.observe(viewLifecycleOwner) {
            asanaAdapter.submitList(it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.asanasEvent.collect{event->
                when(event){
                    is AsanasViewModel.AsanasEvent.NavigateToDetailsScreen -> {
                        val action = AsanaFragmentDirections.actionAsanaFragmentToDetailsFragment2(event.asana)
                        findNavController().navigate(action)
                    }

                   is AsanasViewModel.AsanasEvent.NavigateToProgressScreen -> {
                       val action = AsanaFragmentDirections.actionAsanaFragmentToProgressFragment()
                       findNavController().navigate(action)
                   }
                }

            }.exhaustive
        }

        addMenu()
    }

    override fun onItemClick(asana: AsanaWithType) {
        viewModel.onAsanaClick(asana)
    }

    override fun onImgCompletedClick(asana: Asana) {
        viewModel.updateAsanaCompletion(asana)
    }


    private fun addMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_fragment_asanas, menu)
                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView

                searchView.onQueryTextChanged{
                    viewModel.searchQuery.value=it
                }
                viewLifecycleOwner.lifecycleScope.launch {
                    menu.findItem(R.id.action_hide_completed_tasks).isChecked=viewModel.preferencesFlow.first().hideCompleted
                }
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_show_progress->{
                        viewModel.onProgressIconClicked()
                        true
                    }
                    R.id.action_sort_by_name->{
                        viewModel.onSortOrderSelected(SortOrder.BY_NAME)
                        true
                    }
                    R.id.action_sort_by_sanskrit->{
                        viewModel.onSortOrderSelected(SortOrder.BY_SANSKRIT)
                        true
                    }
                    R.id.action_sort_by_difficulty->{
                        viewModel.onSortOrderSelected(SortOrder.BY_DIFFICULTY)
                        true
                    }
                    R.id.action_sort_by_completed->{
                        viewModel.onSortOrderSelected(SortOrder.BY_COMPLETED)
                        true
                    }
                    R.id.action_sort_by_uncompleted->{
                        viewModel.onSortOrderSelected(SortOrder.BY_UNCOMPLETED)
                        true
                    }
                    R.id.action_sort_by_type->{
                        viewModel.onSortOrderSelected(SortOrder.BY_TYPE)
                        true
                    }
                    R.id.action_hide_completed_tasks->{
                        menuItem.isChecked=!menuItem.isChecked
                        viewModel.onHideCompletedClicked(menuItem.isChecked)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)
    }

}