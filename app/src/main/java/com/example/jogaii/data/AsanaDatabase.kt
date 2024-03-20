package com.example.jogaii.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Asana::class,Type::class], version = 1)
abstract class AsanaDatabase : RoomDatabase() {
    abstract fun asanaDao() : AsanaDao

}