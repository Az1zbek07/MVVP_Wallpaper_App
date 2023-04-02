package com.example.wallpaperappmvvp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyUrl::class], version = 1, exportSchema = false)
abstract class MyUrlDatabase: RoomDatabase() {
    abstract val dao: MyUrlDao

    companion object{
        private var instance: MyUrlDatabase? = null
        operator fun invoke(context: Context) = instance?: synchronized(Any()){
            instance?: createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context): MyUrlDatabase{
            return Room.databaseBuilder(
                context,
                MyUrlDatabase::class.java,
                "myUrl.db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}