package com.example.wallpaperappmvvp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyUrlDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMyUrl(myUrl: MyUrl)

    @Query("SELECT * FROM MyUrl ORDER BY id DESC")
    fun getAllUrls(): LiveData<List<MyUrl>>
}