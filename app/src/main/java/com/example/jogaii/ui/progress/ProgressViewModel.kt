package com.example.jogaii.ui.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.jogaii.data.AsanaDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(private val asanaDao: AsanaDao):ViewModel(){
    val asanaTypesProgress = asanaDao.getAsanaTypesProgress().asLiveData()
}