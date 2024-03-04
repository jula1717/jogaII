package com.example.jogaii.ui.asanas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.jogaii.data.AsanaDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class AsanasViewModel @Inject constructor(
    private val asanaDao: AsanaDao
):ViewModel(){
    val searchQuery = MutableStateFlow("")
    val asanasFlow = searchQuery.flatMapLatest {
        asanaDao.getAsanas(it)
    }
    val asanas = asanasFlow.asLiveData()
}