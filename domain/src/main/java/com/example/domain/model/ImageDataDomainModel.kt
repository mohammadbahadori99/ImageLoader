package com.example.domain.model


data class MyResponseDomainModel(
    val files: List<ImageDataDomainModel>,
    val status: String
)

data class StatsDomainModel(
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

data class ImageDataDomainModel(
    val path: String,
    val stats: StatsDomainModel,
    val isSoftDeleted: Boolean = false,
    var existedItem: Boolean = false,
    var isModified: Boolean = false,
    var isNewItem: Boolean = true,
)