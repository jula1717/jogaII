package com.example.jogaii.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AsanaWithType (
    val id: Int,
    val sanskritName: String,
    val name: String,
    val description: String,
    val columnTypeId: Int,
    val difficulty: Int,
    val imgRes: Int,
    val completed: Boolean,
    val typeName: String
):Parcelable