package com.example.jogaii.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jogaii.data.Asana
import com.example.jogaii.data.AsanaDao
import com.example.jogaii.data.AsanaWithType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val asanaDao: AsanaDao
) : ViewModel() {

    var imgVisible = MutableLiveData<Boolean>().apply { value = true }

    private val detailsEventChannel = Channel<DetailsEvent>()
    val detailsEvent = detailsEventChannel.receiveAsFlow()

    val asana = state.get<AsanaWithType>("asana")

    var asanaName = state.get<String>("asanaName") ?: asana?.asana?.name ?: ""
        set(value) {
            field = value
            state.set("asanaName", value)
        }

    var sanskritName = state.get<String>("sanskritName") ?: asana?.asana?.sanskritName ?: ""
        set(value) {
            field = value
            state.set("sanskritName", value)
        }

    var description = state.get<String>("description") ?: asana?.asana?.description ?: ""
        set(value) {
            field = value
            state.set("description", value)
        }

    var difficulty = state.get<Int>("difficulty") ?: asana?.asana?.difficulty ?: 0
        set(value) {
            field = value
            state.set("difficulty", value)
        }

    var imgRes = state.get<Int>("imgRes") ?: asana?.asana?.imgRes ?: 0
        set(value) {
            field = value
            state.set("imgRes", value)
        }

    var completed = state.get<Boolean>("completed") ?: asana?.asana?.completed ?: false
        set(value) {
            field = value
            state.set("completed", value)
        }

    var type = state.get<String>("type") ?: asana?.type?.typeName ?: ""
        set(value) {
            field = value
            state.set("type", value)
        }

    fun toggleImageVisibility() {
        imgVisible.value = !(imgVisible.value ?: true)
    }

    fun imgDoneClicked() {
        val updatedAsana: Asana? = asana?.asana?.copy(completed = completed)
        updatedAsana?.let {
            updateAsana(it)
            changeImgDone(completed)
        }
    }

    private fun updateAsana(updatedAsana: Asana) = viewModelScope.launch {
        asanaDao.update(updatedAsana)
    }

    private fun changeImgDone(completed: Boolean) = viewModelScope.launch {
        detailsEventChannel.send(DetailsEvent.changeImgDone(completed))
    }

    sealed class DetailsEvent {
        data class changeImgDone(val completed: Boolean) : DetailsEvent()
    }

}