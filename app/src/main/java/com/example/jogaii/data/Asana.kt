package com.example.jogaii.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "asanas_table")
data class Asana(
    val sanskritName: String,
    val name: String,
    val description: String,
    val columnTypeId: Int,
    val difficulty: Int,
    val imgRes: Int,
    val completed: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0

) : Parcelable {
}