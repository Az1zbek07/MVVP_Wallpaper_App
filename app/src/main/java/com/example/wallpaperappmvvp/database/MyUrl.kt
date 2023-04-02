package com.example.wallpaperappmvvp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
@Entity
data class MyUrl(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val url: String
): Parcelable
