package com.example.jogaii.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "types_table")
data class Type(
    val typeName: String,
    val typeImgRes: Int,
    @PrimaryKey(autoGenerate = true) val typeId: Int = 0
) : Parcelable {
}