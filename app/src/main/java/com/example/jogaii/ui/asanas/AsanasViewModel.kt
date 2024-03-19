package com.example.jogaii.ui.asanas

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.jogaii.data.Asana
import com.example.jogaii.data.AsanaDao
import com.example.jogaii.data.AsanaWithType
import com.example.jogaii.data.PreferencesManager
import com.example.jogaii.data.SortOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AsanasViewModel @Inject constructor(
    private val asanaDao: AsanaDao,
    private val preferencesManager: PreferencesManager,
    private val stateHandle: SavedStateHandle
) : ViewModel() {
    val searchQuery = stateHandle.getLiveData<String>("searchQuery","")
    val preferencesFlow = preferencesManager.preferencesFlow

    private val asanasEventChannel = Channel<AsanasEvent>()
    val asanasEvent = asanasEventChannel.receiveAsFlow()

    val asanasFlow = combine(searchQuery.asFlow(), preferencesFlow) { query, preferences ->
        Pair(query, preferences)
    }.flatMapLatest { (mQuery, mPreferences) ->
        asanaDao.getAsanas(mQuery, mPreferences.sortOrder,mPreferences.hideCompleted)
    }

    val asanas = asanasFlow.asLiveData()


    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClicked(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun updateAsanaCompletion(asana: Asana) = viewModelScope.launch {
        val newValue = !asana.completed
        asanaDao.update(asana.copy(completed = newValue))
    }


    fun onAsanaClick(asana: AsanaWithType) = viewModelScope.launch{
        asanasEventChannel.send(AsanasEvent.NavigateToDetailsScreen(asana))
    }

    sealed class AsanasEvent {
        data class NavigateToDetailsScreen(val asana: AsanaWithType) : AsanasEvent()
    }

}

