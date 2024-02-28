package com.example.jogaii.ui.asanas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.jogaii.data.AsanaDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AsanasViewModel @Inject constructor(
    private val asanaDao: AsanaDao
):ViewModel(){
    val asanas = asanaDao.getAsanas().asLiveData()
}