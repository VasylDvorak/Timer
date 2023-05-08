package com.example.timer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timer.domain.RepositoryStopwatchListOrchestrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val textTimer : MutableLiveData<String> = MutableLiveData(),
    private val stopwatchListOrchestrator: RepositoryStopwatchListOrchestrator = RepositoryStopwatchListOrchestrator()
) : ViewModel() {

    fun getTextForTimer(): MutableLiveData<String> {
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        ).launch {
            stopwatchListOrchestrator.ticker.collect {
                textTimer.postValue( it)
            }
        }

        return textTimer
    }


   fun start(){
       stopwatchListOrchestrator.start()
   }

    fun pause(){
        stopwatchListOrchestrator.pause()
    }
    fun stop(){
        stopwatchListOrchestrator.stop()
    }


}