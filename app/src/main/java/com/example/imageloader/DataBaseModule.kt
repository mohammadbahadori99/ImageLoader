package com.example.imageloader

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ImageDatabase::class.java,
            "ImageDataBase"
        ).addCallback(DB_CALLBACK)
            .build()

    @Singleton
    @Provides
    fun provideDao(database: ImageDatabase) = database.imageDao()


    private val DB_CALLBACK = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            db.execSQL(
                "CREATE TRIGGER mytriger \n" +
                        "AFTER UPDATE ON image_data\n" +
                        "WHEN new.isSoftDeleted <>  1  AND (new.atime <> old.atime OR new.atimeMs <> old.atimeMs OR " +
                        "new.birthtime <> old.birthtime OR " +
                        "new.birthtimeMs <> old.birthtimeMs OR " +
                        "new.blksize <> old.blksize OR " +
                        "new.blocks <> old.blocks OR " +
                        "new.ctime <> old.ctime OR " +
                        "new.ctimeMs <> old.ctimeMs OR " +
                        "new.dev <> old.dev OR " +
                        "new.gid <> old.gid OR " +
                        "new.ino <> old.ino OR " +
                        "new.mode <> old.mode OR " +
                        "new.mtime <> old.mtime OR " +
                        "new.mtimeMs <> old.mtimeMs OR " +
                        "new.nlink <> old.nlink OR " +
                        "new.rdev <> old.rdev OR " +
                        "new.size <> old.size OR " +
                        "new.uid <> old.uid ) " +

                        "BEGIN\n" +
                        "INSERT INTO image_data(path,isSoftDeleted,existedItem,isModified,isNewItem, " +
                        "atime," +
                        "atimeMs," +
                        "birthtime," +
                        "birthtimeMs," +
                        "blksize," +
                        "blocks," +
                        "ctime," +
                        "ctimeMs," +
                        "dev," +
                        "gid," +
                        "ino," +
                        "mode," +
                        "mtime," +
                        "mtimeMs," +
                        "nlink," +
                        "rdev," +
                        "size," +
                        "uid)\n" +
                        "VALUES(new.path,new.isSoftDeleted,new.existedItem,1,new.isNewItem, " +
                        "new.atime," +
                        "new.atimeMs," +
                        "new.birthtime," +
                        "new.birthtimeMs," +
                        "new.blksize," +
                        "new.blocks," +
                        "new.ctime," +
                        "new.ctimeMs," +
                        "new.dev," +
                        "new.gid," +
                        "new.ino," +
                        "new.mode," +
                        "new.mtime," +
                        "new.mtimeMs," +
                        "new.nlink," +
                        "new.rdev," +
                        "new.size," +
                        "new.uid) ;\n" +
                        "END;"
            )

        }
    }
}