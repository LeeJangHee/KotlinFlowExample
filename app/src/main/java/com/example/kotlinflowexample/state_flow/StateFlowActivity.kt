package com.example.kotlinflowexample.state_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflowexample.R
import com.example.kotlinflowexample.databinding.ActivityStateFlowBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class StateFlowActivity : AppCompatActivity() {
    private companion object {
        const val TAG = "StateFlowActivity"
    }
    private lateinit var binding: ActivityStateFlowBinding
    private val viewModel by viewModels<StateFlowViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStateFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStateFlow1.setOnClickListener {
            lifecycleScope.launchWhenStarted {
//                viewModel.clickStateFlowA()
//                    .map { num -> "StateFlowA $num" }
//                    .collect { str -> binding.tvStateFlowA.text = str }
                viewModel.setStateFlowA()
            }
        }

        binding.btnStateFlow2.setOnClickListener {
            lifecycleScope.launchWhenStarted {
//                viewModel.clickStateFlowB()
//                    .map { num -> "StateFlowB $num" }
//                    .collect { str -> binding.tvSatateFlowB.text = str }
                viewModel.setStateFlowB()
            }
        }

        binding.btnStateFlow3.setOnClickListener {
            lifecycleScope.launchWhenStarted {
//                viewModel.clickStateFlowC()
//                    .map { num -> "StateFlowC $num" }
//                    .collect { str -> binding.tvStateFlowC.text = str }
                viewModel.setStateFlowC()
            }
        }
        observeStateFlow()
    }

    private fun observeStateFlow() = lifecycleScope.launchWhenStarted {
        viewModel.stateFlow.collect {
            binding.tvStateFlowA.text = it
            binding.tvStateFlowB.text = it
            binding.tvStateFlowC.text = it
        }

        // 같은 stateFlow.collect 를 사용하면 2번째
        // collect 부터 emit 한 데이터가 오지 않는다.
        viewModel.stateFlow.collect {
            binding.tvStateFlowB.text = it
        }

        viewModel.stateFlow.collect {
            binding.tvStateFlowC.text = it
        }
    }
}