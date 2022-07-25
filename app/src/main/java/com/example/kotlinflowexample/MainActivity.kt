package com.example.kotlinflowexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflowexample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLiveData.setOnClickListener {
            viewModel.triggerLiveData()
        }

        binding.btnFlow.setOnClickListener {
            lifecycleScope.launch {
                viewModel.triggerFlow().collect {
                    binding.tvFlow.text = it
                }
            }
        }

        binding.btnStateFlow.setOnClickListener {
            viewModel.triggerStateFlow()
        }

        binding.btnSharedFlow.setOnClickListener {
            viewModel.triggerSharedFlow()
        }

        subscribeToObservables()
    }

    private fun subscribeToObservables() {
        viewModel.liveData.observe(this) {
            binding.tvLiveData.text = it
        }

        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collect {
                binding.tvStateFlow.text = it
//                Snackbar.make(
//                    binding.root,
//                    it,
//                    Snackbar.LENGTH_SHORT
//                ).setAction("OK", null)
//                    .show()
            }


        }
        lifecycleScope.launchWhenStarted {
            viewModel.sharedFlow.collectLatest {
                binding.tvSharedFlow.text = it
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_SHORT
                ).setAction("OK", null)
                    .show()
            }
        }
    }
}