package com.example.imageloader

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllImages(list: List<ImageData>): List<Long>

    @Query("UPDATE image_data SET isSoftDeleted = 1 , isNewItem = 0")
    suspend fun setSoftDeleteTrue()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateList(list:List<ImageData>):Int

    @Query("SELECT * FROM image_data")
    suspend fun getAllImageData(): List<ImageData>

}