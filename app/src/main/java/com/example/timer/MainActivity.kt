package com.example.timer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        operateTimer()
    }

    private fun operateTimer() {
        binding?.apply {

        viewModel.getTextForTimer().observe(this@MainActivity) {
            textTime.text =it
        }

        buttonStart.setOnClickListener {
            viewModel.start()
        }
        buttonPause.setOnClickListener {
            viewModel.pause()
        }
        buttonStop.setOnClickListener {
            viewModel.stop()
        }
    }
}
    override fun onDestroy() {
        super.onDestroy()
        binding =null
    }
}




