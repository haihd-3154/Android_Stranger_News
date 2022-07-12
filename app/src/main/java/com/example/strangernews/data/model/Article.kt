package com.example.strangernews.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_article")
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("url")
    var url: String = "",
    @SerializedName("source")
    var source: String = "",
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("category")
    var category: String = "",
    @SerializedName("language")
    var language: String = "",
    @SerializedName("country")
    var country: String = "",
    @SerializedName("published_at")
    var published_at: String = "",
) : Parcelable {
    @Ignore
    var isFavorite: Boolean = false
}
