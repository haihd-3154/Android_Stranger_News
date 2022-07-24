package com.example.strangernews.utils.extension

import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

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

fun View.showSnackBar(message: String, color: Int) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
        .setBackgroundTint(
            ContextCompat.getColor(
                context, color
            )
        )
        .show()
}
