package com.said.myfavoriteanimals.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.said.myfavoriteanimals.data.db.dao.AnimalDao
import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.data.db.entity.Animal2

@Database(entities = [Animal::class, Animal2::class], version = 4, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE animals ADD COLUMN name TEXT DEFAULT '' NOT NULL")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE animals ADD COLUMN age INTEGER DEFAULT 0 NOT NULL")
            }

        }
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `animals2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `imgUrl` TEXT NOT NULL)");
            }
        }
    }
}