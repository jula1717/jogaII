package com.example.jogaii.data

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize

@Parcelize
data class AsanaWithType (
    @Embedded val asana: Asana,
    @Embedded val type: Type
):Parcelable