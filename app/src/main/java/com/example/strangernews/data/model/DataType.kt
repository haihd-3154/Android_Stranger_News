package com.example.strangernews.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.example.strangernews.utils.constant.ResourceColor
import com.example.strangernews.utils.constant.ResourceIcon
import com.example.strangernews.utils.constant.TypeOfSource
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataType(
    var id: String = "",
    var type: String = TypeOfSource.CATEGORY.name,
    var name: String = "",
    var colorFromResource: Int = ResourceColor.RED,
    var iconFromResource: Int = ResourceIcon.GENERAL,
    var image: String = ""
) : Parcelable {

    fun compare(dataType: DataType): Boolean = this.name == dataType.name && this.id == dataType.id

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataType>() {
            override fun areItemsTheSame(oldItem: DataType, newItem: DataType): Boolean {
                return oldItem.id == newItem.id && oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: DataType, newItem: DataType): Boolean {
                return oldItem == newItem
            }
        }
    }
}
