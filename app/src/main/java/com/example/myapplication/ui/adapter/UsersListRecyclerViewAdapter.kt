package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.R
import com.example.myapplication.domain.model.User
import com.example.myapplication.ui.tools.Constants.USER_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

private val usrDateFmt = SimpleDateFormat(USER_DATE_FORMAT, Locale.US)

class UsersListRecyclerViewAdapter : BaseRecyclerViewAdapter<User>(USER_DIFF) {

    var onSelectItemListener: ((User?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_user_data, parent, false)
        return UsersListViewHolder(v)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<User>, position: Int) {
        bindViewHolder(holder as UsersListViewHolder, position)
    }

    private fun bindViewHolder(holder: UsersListViewHolder, pos: Int) {
        val item = getItem(pos)
        item ?: return

        holder.item = item
        holder.itemPos = pos

        holder.listener = { p, itm -> selectItem(itm) }

        holder.bindView(item)
    }

    private fun selectItem(item: User?) {
        onSelectItemListener?.invoke(item)
    }

    class UsersListViewHolder(itemView: View) : BaseViewHolder<User>(itemView) {

        var listener: ((Int, User?) -> Unit)? = null
        var item: User? = null
        var itemPos: Int = -1

        override fun bindView(item: User) {

            val idText: TextView = itemView.findViewById(R.id.idText)
            val nameText: TextView = itemView.findViewById(R.id.nameText)
            val dateText: TextView = itemView.findViewById(R.id.dateText)

            val clickListener = View.OnClickListener { listener?.invoke(itemPos, item) }

            idText.setOnClickListener(clickListener)
            nameText.setOnClickListener(clickListener)
            dateText.setOnClickListener(clickListener)

            idText.text = item.id
            nameText.text = item.name
            dateText.text = usrDateFmt.format(item.date)
        }
    }

    companion object {
        private val USER_DIFF = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean =
                oldItem.id == newItem.id &&
                oldItem.name == newItem.name &&
                oldItem.date == newItem.date
        }
    }
}
