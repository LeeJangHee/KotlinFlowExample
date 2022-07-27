package com.example.kotlinflowexample.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflowexample.databinding.ActivityFlowBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class FlowActivity : AppCompatActivity() {
    private companion object {
        const val TAG = "FlowActivity"
    }
    private lateinit var binding: ActivityFlowBinding
    private val flowViewModel by viewModels<FlowViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFlow1.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                flowViewModel.clickFlowA()
                    .onEach { data -> Log.v(TAG, "Before map Flow A --> $data") }
                    .map { num -> "FlowA $num" }
                    .onEach { data -> Log.i(TAG, "After map Flow A --> $data") }
                    .collect { str ->
                        binding.tvFlow1.text = str
                    }
            }
        }

        binding.btnFlow2.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                flowViewModel.clickFlowB()
                    .onEach { data -> Log.v(TAG, "Before map Flow A --> $data") }
                    .map { num -> "FlowB $num" }
                    .onEach { data -> Log.i(TAG, "After map Flow A --> $data") }
                    .collect { str ->
                        binding.tvFlow2.text = str
                    }
            }
        }

        binding.btnFlow3.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                flowViewModel.clickFlowC()
                    .onEach { data -> Log.v(TAG, "Before map Flow C --> $data") }
                    .map { num -> "FlowC $num" }
                    .onEach { data -> Log.i(TAG, "After map Flow C --> $data") }
                    .collect { str ->
                        binding.tvFlow3.text = str
                    }
            }
        }
    }
}