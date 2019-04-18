package me.suttichai.develop.koinexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_character")
data class Character(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "index")
    @Expose
    val index: Int,
    @SerializedName("id")
    @ColumnInfo(name = "col_id")
    val id: String,
    @ColumnInfo(name = "col_name")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "col_image_url")
    @SerializedName("imageUrl")
    val imageUrl: String
)