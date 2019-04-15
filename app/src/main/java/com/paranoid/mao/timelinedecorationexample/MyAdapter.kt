package com.paranoid.mao.timelinedecorationexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
        private val items: List<Item>
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(items[position])

    class MyViewHolder(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item) {
            val view = itemView.findViewById<TextView>(R.id.content)
            view.text = item.content
        }
    }
}
