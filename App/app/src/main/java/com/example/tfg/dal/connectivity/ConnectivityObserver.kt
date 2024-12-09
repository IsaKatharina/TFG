package com.example.tfg.dal.connectivity

import kotlinx.coroutines.flow.Flow


interface ConnectivityObserver {
    fun observe(): Flow<Status>

    enum class Status{
        Available,Losing, Lost, Unavailable
    }
}