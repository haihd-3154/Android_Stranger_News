package com.example.strangernews.utils.extension

import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.PopupMenu

fun View.showPopup(
    menu: Int,
    listener: PopupMenu.OnMenuItemClickListener?
) {
    val popup = PopupMenu(context, this)
    val inflater: MenuInflater = popup.menuInflater
    inflater.inflate(menu, popup.menu)
    popup.setOnMenuItemClickListener(listener)
    popup.show()
}