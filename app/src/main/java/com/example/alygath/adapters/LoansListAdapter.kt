package com.example.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R

class MyAdapter(
    private val items: List<MyModel>,
    private val listener: OnItemClickListener? = null
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: MyModel, position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  
        val title = itemView.findViewById<TextView>(R.id.title)

        fun bind(item: MyModel, position: Int) {

            // Bind your data here
            title.text = item.title

            itemView.setOnClickListener {
                listener?.onItemClick(item, position)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}