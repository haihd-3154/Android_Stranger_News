package com.example.strangernews.ui.adapter

import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.strangernews.data.model.DataType
import com.example.strangernews.data.model.DataType.Companion.DIFF_CALLBACK
import com.example.strangernews.databinding.LayoutDatatypeItemBinding
import com.example.strangernews.utils.constant.TypeOfSource
import com.example.strangernews.utils.extension.getResourceColor

class HomeDataTypeAdapter(private val listener: ItemClickListener<DataType>) :
    ListAdapter<DataType, HomeDataTypeAdapter.ViewHolder>(DIFF_CALLBACK) {
    private val iconPadding = 25

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutDatatypeItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                item = this
                binding.apply {
                    when (type) {
                        TypeOfSource.CATEGORY.name -> {
                            imgDataType.apply {
                                load(iconFromResource) {
                                    crossfade(true)
                                }
                                setColorFilter(
                                    context.getResourceColor(android.R.color.white),
                                    PorterDuff.Mode.SRC_ATOP
                                )
                                setPadding(iconPadding, iconPadding, iconPadding, iconPadding)
                                (background as GradientDrawable).setColor(
                                    context.getResourceColor(colorFromResource)
                                )
                            }
                        }
                        TypeOfSource.SOURCE.name -> {
                            imgDataType.load(image) {
                                crossfade(true)
                                transformations(CircleCropTransformation())
                            }
                        }
                        else -> {}
                    }
                    txtDataTypeName.text = name
                }
            }
        }
    }

    class ViewHolder(
        val binding: LayoutDatatypeItemBinding,
        private val listener: ItemClickListener<DataType>
    ) : RecyclerView.ViewHolder(binding.root) {

        var item: DataType? = null

        init {
            itemView.setOnClickListener {
                listener.onItemClick(item)
            }
            itemView.setOnLongClickListener {
                listener.onItemLongClick(item)
            }
        }
    }
}
