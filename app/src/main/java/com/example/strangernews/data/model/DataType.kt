package com.example.strangernews.data.model

import android.os.Parcelable
import com.example.strangernews.utils.constant.ResourceColor
import com.example.strangernews.utils.constant.ResourceIcon
import com.example.strangernews.utils.constant.TypeOfSource
import kotlinx.parcelize.Parcelize

@Parcelize
class DataType(
    var id : String = "",
    var type: String = TypeOfSource.CATEGORY.name,
    var name: String = "",
    var colorFromResource: Int = ResourceColor.RED,
    var iconFromResource: Int = ResourceIcon.GENERAL,
    var image: String = ""
) : Parcelable
