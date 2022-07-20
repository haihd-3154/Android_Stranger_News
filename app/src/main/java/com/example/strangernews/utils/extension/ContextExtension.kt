package com.example.strangernews.utils.extension

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import coil.load
import com.example.strangernews.R
import com.example.strangernews.data.model.Article
import com.example.strangernews.databinding.LayoutBottomSheetBinding
import com.example.strangernews.ui.callback.ArticleBottomSheetCallback
import com.example.strangernews.utils.constant.Constants
import com.example.strangernews.utils.constant.Constants.NEGATIVE_TEXT
import com.example.strangernews.utils.constant.Constants.POSITIVE_TEXT
import com.google.android.material.bottomsheet.BottomSheetDialog

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATASTORE_NAME)

fun Context.getResourceColor(resource: Int) = ContextCompat.getColor(this, resource)

fun Context?.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showArticleBottomSheet(
    ar: Article,
    listener: ArticleBottomSheetCallback,
    open: Boolean = true
) {
    val favorite = listener.checkIsFavorite(ar)
    val bottomSheet = BottomSheetDialog(this)
    val binding = LayoutBottomSheetBinding.inflate(bottomSheet.layoutInflater)
    bottomSheet.setContentView(binding.root)
    binding.apply {
        includeItemFavorite.apply {
            if (favorite) {
                itemIcon.load(R.drawable.ic_bookmark_remove)
                itemTitle.text = Constants.TXT_BOOKMARK_REMOVE
            } else {
                itemIcon.load(R.drawable.ic_bookmark_add)
                itemTitle.text = Constants.TXT_BOOKMARK_ADD
            }
            itemIcon.setOnClickListener {
                listener.favorite(ar,this@showArticleBottomSheet)
                bottomSheet.dismiss()
            }
        }
        includeItemShare.apply {
            itemIcon.load(R.drawable.ic_share)
            itemTitle.text = Constants.TXT_SHARE
            itemIcon.setOnClickListener {
                listener.share(ar, this@showArticleBottomSheet)
                bottomSheet.dismiss()
            }
        }
        includeItemOpen.apply {
            if (open) {
                itemIcon.load(R.drawable.ic_pageview)
                itemTitle.text = Constants.TXT_OPEN
                itemIcon.setOnClickListener {
                    listener.open(ar, this@showArticleBottomSheet)
                    bottomSheet.dismiss()
                }
            } else {
                includeItemOpen.root.visibility = View.GONE
            }
        }
        includeItemBrowser.apply {
            itemIcon.load(R.drawable.ic_airplanemode)
            itemTitle.text = Constants.TXT_OPEN_BROWSER
            itemIcon.setOnClickListener {
                listener.browser(ar, this@showArticleBottomSheet)
                bottomSheet.dismiss()
            }
        }
    }
    bottomSheet.show()
}

fun Context.showSingleChoiceDialog(
    dialogTitle: String,
    source: Set<String>,
    selected: Int,
    onSuccess: (Int) -> Unit
) {
    var checked = selected
    AlertDialog.Builder(this).apply {
        setTitle(dialogTitle)
        setSingleChoiceItems(source.toTypedArray(), checked) { _, item ->
            checked = item
        }
        create()
        setPositiveButton(POSITIVE_TEXT) { _, _ ->
            onSuccess(checked)
        }
        setNegativeButton(NEGATIVE_TEXT) { _, _ ->
        }
    }.show()
}

fun Context.showMultiChoiceDialog(
    dialogTitle: String,
    source: Set<String>,
    checked: BooleanArray,
    onSuccess: (BooleanArray) -> Unit
) {
    AlertDialog.Builder(this).apply {
        setTitle(dialogTitle)
        setMultiChoiceItems(source.toTypedArray(), checked) { _, which, isChecked ->
            checked[which] = isChecked
        }
        create()
        setPositiveButton(POSITIVE_TEXT) { _, _ ->
            onSuccess(checked)
        }
        setNegativeButton(NEGATIVE_TEXT) { _, _ ->
        }
    }.show()
}

fun Context.showDatePickerDialog(default: String? = null, onSuccess: (String) -> Unit) {
    val defaultTime = default?.getDateData()
    DatePickerDialog(this).apply {
        defaultTime?.let {
            updateDate(it[0], it[1], it[2])
        }
        setOnDateSetListener { _, year, month, day ->
            onSuccess("$year-${month+1}-$day")
        }
        create()
    }.show()
}
