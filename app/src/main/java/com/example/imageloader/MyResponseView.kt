package com.example.imageloader

import com.example.domain.model.ImageDataDomainModel
import com.example.domain.model.StatsDomainModel


data class MyResponseView(
    val files: List<ImageDataView>,
    val status: String
)

data class StatsView(
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

data class ImageDataView(
    val path: String,
    val stats: StatsView,
    val isSoftDeleted: Boolean = false,
    var existedItem: Boolean = false,
    var isModified: Boolean = false,
    var isNewItem: Boolean = true,
)


fun StatsDomainModel.toStatsView() = StatsView(
    atime,
    atimeMs,
    birthtime,
    birthtimeMs,
    blksize,
    blocks,
    ctime,
    ctimeMs,
    dev,
    gid,
    ino,
    mode,
    mtime,
    mtimeMs,
    nlink,
    rdev,
    size,
    uid
)

fun ImageDataDomainModel.toImageDataView() = ImageDataView(
    path = this.path, stats = this.stats.toStatsView(), isSoftDeleted = isSoftDeleted,
    existedItem = existedItem, isModified = isModified, isNewItem = isNewItem
)