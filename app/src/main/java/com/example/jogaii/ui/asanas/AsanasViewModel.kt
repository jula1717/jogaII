package com.example.jogaii.ui.asanas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.jogaii.data.AsanaDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class AsanasViewModel @Inject constructor(
    private val asanaDao: AsanaDao
) : ViewModel() {
    val searchQuery = MutableStateFlow("")
    val sortOrder = MutableStateFlow(SortOrder.BY_SANSKRIT)
    val hideCompleted = MutableStateFlow(false)

    val asanasFlow = combine(searchQuery, sortOrder, hideCompleted) { query, sort, hide ->
        Triple(query, sort, hide)
    }.flatMapLatest { (mQuery, mSort, mHide) ->
        asanaDao.getAsanas(mQuery, mSort, mHide)
    }

    val asanas = asanasFlow.asLiveData()
}

enum class SortOrder { BY_NAME, BY_SANSKRIT, BY_DIFFICULTY, BY_COMPLETED, BY_UNCOMPLETED, BY_TYPE }