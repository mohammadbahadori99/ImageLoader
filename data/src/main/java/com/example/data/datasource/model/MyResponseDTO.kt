package com.example.data.datasource.model

import androidx.room.Embedded
import androidx.room.Entity
import com.example.domain.model.ImageDataDomainModel
import com.example.domain.model.MyResponseDomainModel
import com.example.domain.model.StatsDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class MyResponseDTO(
    val files: List<ImageDataEntity>,
    val status: String
)

@Serializable
data class StatsEntity(
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

@Entity(tableName = "image_data", primaryKeys = ["path"])
@Serializable
data class ImageDataEntity(
    val path: String,
    @Embedded
    val stats: StatsEntity,
    val isSoftDeleted: Boolean = false,
    var existedItem: Boolean = false,
    var isModified: Boolean = false,
    var isNewItem: Boolean = true,
)

fun StatsEntity.toStatsDomainModel() = StatsDomainModel(
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

fun ImageDataEntity.toImageDataDomainModel() = ImageDataDomainModel(
    path = this.path,
    stats = this.stats.toStatsDomainModel(),
    isSoftDeleted = this.isSoftDeleted,
    existedItem = this.existedItem,
    isModified = this.isModified,
    isNewItem = this.isNewItem
)

fun MyResponseDTO.toMyResponseDomainModel() =
    MyResponseDomainModel(files = this.files.map { it.toImageDataDomainModel() }, status = status)


fun StatsDomainModel.toStatsEntity() = StatsEntity(
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

fun ImageDataDomainModel.toImageDataEntity() =
    ImageDataEntity(path, stats.toStatsEntity(), isSoftDeleted, existedItem, isModified, isNewItem)

fun MyResponseDomainModel.toMyResponseDTO() =
    MyResponseDTO(this.files.map { it.toImageDataEntity() }, status = this.status)