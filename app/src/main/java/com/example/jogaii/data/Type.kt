package com.example.jogaii.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "types_table")
data class Type(
    val name: String,
    val imgRes: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable {
}