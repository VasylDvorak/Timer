package com.example.timer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timer.domain.RepositoryStopwatchListOrchestrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val textTimerFirst: MutableLiveData<String> = MutableLiveData(),
    private val textTimerSecond: MutableLiveData<String> = MutableLiveData()
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val stopwatchListOrchestratorFirst: RepositoryStopwatchListOrchestrator
            = RepositoryStopwatchListOrchestrator()
    private val stopwatchListOrchestratorSecond: RepositoryStopwatchListOrchestrator
            = RepositoryStopwatchListOrchestrator()

    fun getTextForTimerFirst(): MutableLiveData<String> {
        coroutineScope.launch {
            stopwatchListOrchestratorFirst.ticker.collect {
                textTimerFirst.postValue(it)
            }
        }
        return textTimerFirst
    }

    fun startFirst() {
        stopwatchListOrchestratorFirst.start()
    }

    fun pauseFirst() {
        stopwatchListOrchestratorFirst.pause()
    }

    fun stopFirst() {
        stopwatchListOrchestratorFirst.stop()
    }

    fun getTextForTimerSecond(): MutableLiveData<String> {
        coroutineScope.launch {
            stopwatchListOrchestratorSecond.ticker.collect {
                textTimerSecond.postValue(it)
            }
        }
        return textTimerSecond
    }

    fun startSecond() {
        stopwatchListOrchestratorSecond.start()
    }

    fun pauseSecond() {
        stopwatchListOrchestratorSecond.pause()
    }

    fun stopSecond() {
        stopwatchListOrchestratorSecond.stop()
    }
}