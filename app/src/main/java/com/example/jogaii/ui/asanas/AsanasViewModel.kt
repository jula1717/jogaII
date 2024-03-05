package com.example.jogaii.ui.asanas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.jogaii.data.AsanaDao
import com.example.jogaii.data.PreferencesManager
import com.example.jogaii.data.SortOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AsanasViewModel @Inject constructor(
    private val asanaDao: AsanaDao,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    val searchQuery = MutableStateFlow("")
    val preferencesFlow = preferencesManager.preferencesFlow

    val asanasFlow = combine(searchQuery, preferencesFlow) { query, preferences ->
        Pair(query, preferences)
    }.flatMapLatest { (mQuery, mPreferences) ->
        asanaDao.getAsanas(mQuery, mPreferences.sortOrder,mPreferences.hideCompleted)
    }

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClicked(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    val asanas = asanasFlow.asLiveData()
}

