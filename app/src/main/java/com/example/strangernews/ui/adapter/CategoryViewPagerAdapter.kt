package com.example.strangernews.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.strangernews.R
import com.example.strangernews.data.model.DataType
import com.example.strangernews.databinding.LayoutCategoryViewpagerItemBinding
import com.example.strangernews.ui.callback.UpdatePositionCallback
import com.example.strangernews.utils.constant.TypeOfSource

class CategoryViewPagerAdapter(private val listener: UpdatePositionCallback) :
    ListAdapter<DataType, CategoryViewPagerAdapter.ViewHolder>(DataType.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutCategoryViewpagerItemBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                item = this
                binding.apply {
                    item?.apply {
                        imageDatatypeViewPager.load(image) {
                            crossfade(true)
                            placeholder(R.drawable.img_default)
                            error(R.drawable.img_default)
                        }
                        if (type == TypeOfSource.LANGUAGE.name || type == TypeOfSource.CATEGORY.name) {
                            txtDataTypeName.text = name
                        }
                        previousDataType.apply {
                            if (position==0){
                                visibility = View.GONE
                            } else{
                                visibility = View.VISIBLE
                                setOnClickListener {
                                    listener.update(position-1)
                                }
                            }
                        }
                        nextDataType.apply {
                            if (position==currentList.size-1){
                                visibility = View.GONE
                            } else{
                                visibility = View.VISIBLE
                                setOnClickListener {
                                    listener.update(position+1)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    class ViewHolder(
        val binding: LayoutCategoryViewpagerItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        var item: DataType? = null
    }
}
