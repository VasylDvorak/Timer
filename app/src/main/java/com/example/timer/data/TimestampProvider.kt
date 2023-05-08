package com.example.timer.data

interface TimestampProvider {
    fun getMilliseconds(): Long
}