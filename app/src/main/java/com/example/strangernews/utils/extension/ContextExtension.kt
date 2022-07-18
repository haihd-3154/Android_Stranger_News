package com.example.strangernews.utils.extension

import android.content.Context
import android.view.View
import android.widget.Toast
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
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.runBlocking

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATASTORE_NAME)

fun Context.getResourceColor(resource: Int) = ContextCompat.getColor(this, resource)

fun Context?.showToast(message: String): Toast? = Toast.makeText(this, message, Toast.LENGTH_SHORT)

fun Context.showArticleBottomSheet(ar: Article,listener: ArticleBottomSheetCallback, open: Boolean = true) {
    listener.checkIsFavorite(ar)
    val bottomSheet = BottomSheetDialog(this)
    val binding = LayoutBottomSheetBinding.inflate(bottomSheet.layoutInflater)
    bottomSheet.setContentView(binding.root)
    binding.apply {
        includeItemFavorite.apply {
            if (ar.isFavorite) {
                itemIcon.load(R.drawable.ic_bookmark_remove)
                itemTitle.text = Constants.TXT_BOOKMARK_REMOVE
            } else {
                itemIcon.load(R.drawable.ic_bookmark_add)
                itemTitle.text = Constants.TXT_BOOKMARK_ADD
            }
            itemIcon.setOnClickListener {
                listener.favorite(ar)
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
                    listener.open(ar,this@showArticleBottomSheet)
                    bottomSheet.dismiss()
                }
            } else {
                includeItemOpen.root.visibility= View.GONE
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
