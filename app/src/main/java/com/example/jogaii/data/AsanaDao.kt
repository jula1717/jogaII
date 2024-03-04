package com.example.jogaii.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface AsanaDao {
    @Query ("SELECT * FROM asanas_table WHERE name LIKE '%' || :searchQuery || '%' OR sanskritName LIKE '%' || :searchQuery || '%'")
    fun getAsanas(searchQuery:String): Flow<List<Asana>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asana: Asana)

    @Update
    suspend fun update(asana: Asana)

    @Delete
    suspend fun delete(asana: Asana)

    @Query ("SELECT * FROM types_table")
    fun getTypes(): Flow<List<Type>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(type: Type)

    @Update
    suspend fun update(type: Type)

    @Delete
    suspend fun delete(type: Type)
}