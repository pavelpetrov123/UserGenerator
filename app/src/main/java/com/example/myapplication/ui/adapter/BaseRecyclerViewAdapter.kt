package com.example.myapplication.ui.adapter

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.App

abstract class BaseRecyclerViewAdapter<ItemType>(diffCallback: DiffUtil.ItemCallback<ItemType>
) : ListAdapter<ItemType, BaseRecyclerViewAdapter.BaseViewHolder<ItemType>>(diffCallback) {

    var clickListener: ((item: ItemType?) -> Unit)? = {}

    val context: Context? = App.appInstance

    val resources: Resources?
        get() {
            return context?.resources
        }
    
    abstract class BaseViewHolder<ItemType>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(item: ItemType)
    }
}
