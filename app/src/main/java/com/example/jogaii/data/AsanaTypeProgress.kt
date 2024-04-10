package com.example.jogaii.data

import androidx.room.Embedded

data class AsanaTypeProgress (
    @Embedded val type: Type,
    val totalAsanas: Int,
    val completedAsanas: Int
)