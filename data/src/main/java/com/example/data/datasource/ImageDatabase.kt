package com.example.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.datasource.local.ImageDao
import com.example.data.datasource.model.ImageDataEntity

@Database(entities = [ImageDataEntity::class], version = 1, exportSchema = false)
abstract class ImageDatabase: RoomDatabase() {
    abstract fun imageDao(): ImageDao
}