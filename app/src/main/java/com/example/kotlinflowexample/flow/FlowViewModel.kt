package com.example.kotlinflowexample.flow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowViewModel : ViewModel() {

    private fun triggerFlow(): Flow<Int> {
        return flow {
            repeat(5) {
                delay(2000L)
                emit(it + 1)
            }
        }
    }

    fun clickFlowA(): Flow<Int> = triggerFlow()

    fun clickFlowB(): Flow<Int> = triggerFlow()

    fun clickFlowC(): Flow<Int> = triggerFlow()

}