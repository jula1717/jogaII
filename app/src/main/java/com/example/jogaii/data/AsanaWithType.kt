package com.example.jogaii.data

import androidx.room.Embedded

data class AsanaWithType (
    @Embedded val asana: Asana,
    @Embedded val type: Type
)