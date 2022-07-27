package com.example.kotlinflowexample.state_flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StateFlowViewModel : ViewModel() {

    private val _stateFlow: MutableStateFlow<String> = MutableStateFlow("Hello StateFlow !!")
    val stateFlow get() = _stateFlow.asStateFlow()

    private suspend fun triggerStateFlow(): StateFlow<Int> {
        return flow<Int> {
            repeat(10) {
                delay(1000L)
                emit(it + 1)
            }
        }.stateIn(viewModelScope)
    }

    suspend fun clickStateFlowA(): StateFlow<Int> = triggerStateFlow()

    suspend fun clickStateFlowB(): StateFlow<Int> = triggerStateFlow()

    suspend fun clickStateFlowC(): StateFlow<Int> = triggerStateFlow()

    fun setStateFlowA() = viewModelScope.launch {
        _stateFlow.emit("StateFlow A Click Event")
    }

    fun setStateFlowB() = viewModelScope.launch {
        _stateFlow.emit("StateFlow B Click Event")
    }

    fun setStateFlowC() = viewModelScope.launch {
        _stateFlow.emit("StateFlow C Click Event")
    }
}