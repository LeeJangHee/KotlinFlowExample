package com.example.kotlinflowexample.shared_flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SharedFlowViewModel : ViewModel() {

    private val _sharedFlow: MutableSharedFlow<String> = MutableSharedFlow()
    val sharedFlow get() = _sharedFlow.asSharedFlow()

    private val _replaySharedFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1)
    val replaySharedFlow get() = _replaySharedFlow.asSharedFlow()

    private val _extraBufferCapacitySharedFlow = MutableSharedFlow<String>(extraBufferCapacity = 3)
    val extraBufferCapacitySharedFlow get() = _extraBufferCapacitySharedFlow.asSharedFlow()

    private val _onBufferOverflowSharedFlow = MutableSharedFlow<String>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val onBufferOverflowSharedFlow get() = _onBufferOverflowSharedFlow.asSharedFlow()

    private val _allConstructorSharedFlow = MutableSharedFlow<String>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val allConstructorSharedFlow get() = _allConstructorSharedFlow.asSharedFlow()


    fun setNormal(data: String) = viewModelScope.launch {
        delay(500L)
        _sharedFlow.emit(data)
    }
    fun setReplay(data: String) = viewModelScope.launch {
        delay(500L)
        _replaySharedFlow.emit(data)
    }

    fun setExtraBuffer(data: String) = viewModelScope.launch {
        delay(500L)
        _extraBufferCapacitySharedFlow.emit(data)
    }

    fun setBufferOverflow(data: String) = viewModelScope.launch {
        delay(500L)
        _onBufferOverflowSharedFlow.emit(data)
    }

    fun setAll(data: String) = viewModelScope.launch {
        delay(500L)
        _allConstructorSharedFlow.emit(data)
    }
}