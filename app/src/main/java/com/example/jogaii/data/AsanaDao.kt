package com.example.jogaii.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface AsanaDao {
    fun getAsanas(
        searchQuery: String,
        sortOrder: SortOrder,
        hideCompleted: Boolean
    ): Flow<List<AsanaWithType>> = when (sortOrder) {
        SortOrder.BY_NAME -> getAsanasSortedByName(searchQuery, hideCompleted)
        SortOrder.BY_SANSKRIT -> getAsanasSortedBySanskrit(searchQuery, hideCompleted)
        SortOrder.BY_DIFFICULTY -> getAsanasSortedByDifficulty(searchQuery, hideCompleted)
        SortOrder.BY_COMPLETED -> getAsanasSortedByCompleted(searchQuery, hideCompleted)
        SortOrder.BY_UNCOMPLETED -> getAsanasSortedByUncompleted(searchQuery, hideCompleted)
        SortOrder.BY_TYPE -> getAsanasSortedByType(searchQuery, hideCompleted)
    }

    companion object {
        const val BASE_QUERY =
            "SELECT asanas_table.*, types_table.* FROM asanas_table INNER JOIN types_table ON asanas_table.columnTypeId = types_table.typeId WHERE (asanas_table.completed != :hideCompleted OR asanas_table.completed = 0) AND (asanas_table.name LIKE '%' || :searchQuery || '%' OR asanas_table.sanskritName LIKE '%' || :searchQuery || '%' OR types_table.typeName LIKE '%' || :searchQuery || '%')"
    }

    @Transaction
    @Query("$BASE_QUERY ORDER BY asanas_table.name")
    fun getAsanasSortedByName(
        searchQuery: String,
        hideCompleted: Boolean
    ): Flow<List<AsanaWithType>>

    @Transaction
    @Query("$BASE_QUERY ORDER BY asanas_table.sanskritName")
    fun getAsanasSortedBySanskrit(
        searchQuery: String,
        hideCompleted: Boolean
    ): Flow<List<AsanaWithType>>

    @Transaction
    @Query("$BASE_QUERY ORDER BY asanas_table.difficulty")
    fun getAsanasSortedByDifficulty(
        searchQuery: String,
        hideCompleted: Boolean
    ): Flow<List<AsanaWithType>>

    @Transaction
    @Query("$BASE_QUERY ORDER BY asanas_table.completed ASC")
    fun getAsanasSortedByCompleted(
        searchQuery: String,
        hideCompleted: Boolean
    ): Flow<List<AsanaWithType>>

    @Transaction
    @Query("$BASE_QUERY ORDER BY asanas_table.completed DESC")
    fun getAsanasSortedByUncompleted(
        searchQuery: String,
        hideCompleted: Boolean
    ): Flow<List<AsanaWithType>>

    @Transaction
    @Query("$BASE_QUERY ORDER BY asanas_table.columnTypeId")
    fun getAsanasSortedByType(
        searchQuery: String,
        hideCompleted: Boolean
    ): Flow<List<AsanaWithType>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asana: Asana)

    @Update
    suspend fun update(asana: Asana)

    @Delete
    suspend fun delete(asana: Asana)

    @Query("SELECT * FROM types_table")
    fun getTypes(): Flow<List<Type>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(type: Type)

    @Update
    suspend fun update(type: Type)

    @Delete
    suspend fun delete(type: Type)

    @Query ("SELECT types_table.*, COUNT(asanas_table.asanaId) AS totalAsanas, COUNT(CASE WHEN asanas_table.completed = 1 THEN 1 ELSE NULL END) AS completedAsanas FROM types_table LEFT JOIN asanas_table ON types_table.typeId = asanas_table.columnTypeId GROUP BY types_table.typeId")
    fun getAsanaTypesProgress(): Flow<List<AsanaTypeProgress>>
}