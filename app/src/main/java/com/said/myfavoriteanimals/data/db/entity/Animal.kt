package com.said.myfavoriteanimals.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "animals")
data class Animal(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "imgUrl")
    var imgUrl: String,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "age")
    var age: Int = 0,
) : Parcelable