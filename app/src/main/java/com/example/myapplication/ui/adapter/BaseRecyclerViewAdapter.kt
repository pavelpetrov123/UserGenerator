package com.example.myapplication.ui.adapter

import android.content.Context
import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.App

abstract class BaseRecyclerViewAdapter<D, T: RecyclerView.ViewHolder> (var items: List<D>?) : RecyclerView.Adapter<T>() {

    var clickListener: ((item: D?) -> Unit)? = {}

    val context: Context? = App.appInstance

    val resources: Resources?
        get() {
            return context?.resources
        }

    val count: Int
        get() {
            var count = 0
            items?.let { count = it.size }
            return count
        }

    override fun getItemCount(): Int {
        return count
    }

    fun getItemPos(item: D): Int {
        val pos = items?.indexOf(item)
        return pos ?: -1
    }

    fun updateItems(items: List<D>, doClear: Boolean = true, refreshDataSet: Boolean = true) {
        if (this.items is MutableList) {
           if (doClear) { clear() }
           (this.items as MutableList).addAll(items)
        } else this.items = items
        if (refreshDataSet) {
            notifyDataSetChanged()
        }
    }

    fun clear(refreshDataSet: Boolean = false) {
        if (this.items is MutableList) {
            (this.items as MutableList).clear()
        }
        if (refreshDataSet) {
            notifyDataSetChanged()
        }
    }
}
