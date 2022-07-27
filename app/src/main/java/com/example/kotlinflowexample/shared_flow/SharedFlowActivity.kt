package com.example.kotlinflowexample.shared_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflowexample.R
import com.example.kotlinflowexample.databinding.ActivitySharedFlowBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class SharedFlowActivity : AppCompatActivity() {
    private companion object  {
        const val TAG = "SharedFlowActivity"
    }
    private lateinit var binding: ActivitySharedFlowBinding
    private val viewModel by viewModels<SharedFlowViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnNormal.setOnClickListener {
                lifecycleScope.launchWhenStarted {
                    for (i in 1..5) {
                        delay(500)
                        viewModel.setNormal("Nomal $it")
                    }
                }
            }

            btnReplay.setOnClickListener {
                lifecycleScope.launchWhenStarted {
                    for (i in 1..5) {
                        delay(500)
                        viewModel.setReplay("Replay $it")
                    }
                }
            }

            btnExtraBuffer.setOnClickListener {
                lifecycleScope.launchWhenStarted {
                    for (i in 1..5) {
                        delay(500)
                        viewModel.setExtraBuffer("ExtraBuffer $it")
                    }
                }
            }

            btnBufferOverflow.setOnClickListener {
                lifecycleScope.launchWhenStarted {
                    for (i in 1..5) {
                        delay(500)
                        viewModel.setBufferOverflow("BufferOverflow $it")
                    }
                }
            }

            btnAll.setOnClickListener {
                lifecycleScope.launchWhenStarted {
                    for (i in 1..5) {
                        delay(500)
                        viewModel.setAll("All $it")
                    }
                }
            }

        }


        observeSharedFlow()

    }

    private fun observeSharedFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.sharedFlow
                .onEach { data -> Log.e(TAG, "sharedFlow: $data", ) }
                .collect { binding.tvNormal.text = it }

            viewModel.replaySharedFlow
                .onEach { data -> Log.e(TAG, "replaySharedFlow: $data", ) }
                .collect { binding.tvReplay.text = it }

            viewModel.extraBufferCapacitySharedFlow
                .onEach { data -> Log.e(TAG, "extraBufferCapacitySharedFlow: $data", ) }
                .collect { binding.tvExtraBuffer.text = it }

            viewModel.onBufferOverflowSharedFlow
                .onEach { data -> Log.e(TAG, "onBufferOverflowSharedFlow: $data", ) }
                .collect { binding.tvBufferOverflow.text = it }

            viewModel.allConstructorSharedFlow
                .onEach { data -> Log.e(TAG, "allConstructorSharedFlow: $data") }
                .collect { binding.tvAll.text = it }
        }
    }
}