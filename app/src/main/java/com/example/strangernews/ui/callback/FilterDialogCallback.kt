package com.example.strangernews.ui.callback

import com.example.strangernews.data.model.FilterState

interface FilterDialogCallback {
    fun onCancel()
    fun onUpdate(data: FilterState)
}
