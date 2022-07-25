package com.example.kotlinflowexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var _liveData = MutableLiveData("Hello World!")
    val liveData: LiveData<String> get() = _liveData

    private var _stateFlow = MutableStateFlow("Hello World!")
    val stateFlow get() = _stateFlow.asStateFlow()

    private var _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow get() = _sharedFlow.asSharedFlow()

    fun triggerLiveData() {
        _liveData.value = "LiveData Click"
    }

    fun triggerStateFlow() {
        _stateFlow.value = "StateFlow Click"
    }

    fun triggerFlow(): Flow<String> {
        return flow {
            repeat(5) {
                delay(1000L)
                emit("Flow ${it + 1}")
            }
        }
    }

    fun triggerSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow Click")
        }
    }
}