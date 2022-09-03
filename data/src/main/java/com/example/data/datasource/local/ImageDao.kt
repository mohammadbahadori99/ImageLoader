package com.example.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.datasource.model.ImageDataEntity


@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllImages(list: List<ImageDataEntity>): List<Long>

    @Query("UPDATE image_data SET isSoftDeleted = 1 , isNewItem = 0")
    suspend fun setSoftDeleteTrue()

    @Query("UPDATE image_data SET existedItem = 1 , isNewItem = 0")
    suspend fun setAllDataIsLocal()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateList(list: List<ImageDataEntity>): Int

    @Query("SELECT * FROM image_data")
    suspend fun getAllImageData(): List<ImageDataEntity>

}