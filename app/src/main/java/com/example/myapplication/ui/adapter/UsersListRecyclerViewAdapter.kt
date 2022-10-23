package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.model.User
import com.example.myapplication.ui.tools.Constants.USER_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

class UsersListRecyclerViewAdapter(items: List<User>?)
    : BaseRecyclerViewAdapter<User, UsersListRecyclerViewAdapter.UsersListViewHolder>(items) {

    var onSelectItemListener: ((User?) -> Unit)? = null

    private val usrDateFmt = SimpleDateFormat(USER_DATE_FORMAT, Locale.US)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_user_data, parent, false)
        return UsersListViewHolder(this, v)
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, pos: Int, payloads: MutableList<Any>) {
        val item = items?.get(pos)
        bindViewHolder(holder, pos, item)
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val item = items?.get(position)
        bindViewHolder(holder, position, item)
    }

    private fun bindViewHolder(holder: UsersListViewHolder, pos: Int, item: User?) {
        item ?: return

        holder.item = item
        holder.itemPos = pos

        holder.listener = { p, itm -> selectItem(itm) }

        holder.idText.text = item.id
        holder.nameText.text = item.name
        holder.dateText.text = usrDateFmt.format(item.date)
    }

    private fun selectItem(item: User?) {
        onSelectItemListener?.invoke(item)
    }

    class UsersListViewHolder(val adapter: UsersListRecyclerViewAdapter, var itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var idText: TextView = itemView.findViewById(R.id.idText)
        var nameText: TextView = itemView.findViewById(R.id.nameText)
        var dateText: TextView = itemView.findViewById(R.id.dateText)

        var listener: ((Int, User?) -> Unit)? = null
        var item: User? = null
        var itemPos: Int = -1

        init { initListener() }

        private fun initListener() {
            val clickListener = View.OnClickListener { listener?.invoke(itemPos, item) }
            idText.setOnClickListener(clickListener)
            nameText.setOnClickListener(clickListener)
            dateText.setOnClickListener(clickListener)
        }
    }
}