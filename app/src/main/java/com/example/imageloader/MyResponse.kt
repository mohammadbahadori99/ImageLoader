package com.example.imageloader

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class MyResponse(
    val files: List<ImageData>,
    val status: String
)

@Serializable
data class Stats(
    val atime: String,
    val atimeMs: Long,
    val birthtime: String,
    val birthtimeMs: Double,
    val blksize: Int,
    val blocks: Int,
    val ctime: String,
    val ctimeMs: Double,
    var dev: Int,
    val gid: Int,
    val ino: Int,
    val mode: Int,
    val mtime: String,
    val mtimeMs: Long,
    val nlink: Int,
    val rdev: Int,
    val size: Int,
    val uid: Int
)

@Entity (tableName = "image_data", primaryKeys = ["path"])
@Serializable
data class ImageData(

    val path: String,

    @Embedded
    val stats: Stats,

    val isSoftDeleted: Boolean = false,
    var existedItem: Boolean = false,
    var isModified: Boolean = false,
    var isNewItem: Boolean = true,
)