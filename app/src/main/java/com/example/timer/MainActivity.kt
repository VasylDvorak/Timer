package com.example.timer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        operateFirstTimer()
        operateSecondTimer()
    }

    private fun operateFirstTimer() {
        binding?.apply {
            with(viewModel) {
                getTextForTimerFirst().observe(this@MainActivity) { textTimeOne.text = it }

                buttonStartOne.setOnClickListener { startFirst() }
                buttonPauseOne.setOnClickListener { pauseFirst() }
                buttonStopOne.setOnClickListener { stopFirst() }
            }
        }
    }

    private fun operateSecondTimer() {
        binding?.apply {
            with(viewModel) {
                getTextForTimerSecond().observe(this@MainActivity) { textTimeTwo.text = it }

                buttonStartTwo.setOnClickListener { startSecond() }
                buttonPauseTwo.setOnClickListener { pauseSecond() }
                buttonStopTwo.setOnClickListener { stopSecond() }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}




