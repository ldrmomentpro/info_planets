package com.ldrmomentpro.planets

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(list: MutableList<ListItem>, context: Context) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var listR = list
    var contextR = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.im)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvContent = view.findViewById<TextView>(R.id.tvContent)

        fun bind(listItem: ListItem, context: Context) {
            image.setImageResource(listItem.imageId)
            tvTitle.text = listItem.title
            if (listItem.title != "Солнечная система" && listItem.title != "Солнце") {
                val textContent = listItem.contentText.substring(0, 50) + "..."
                tvContent.text = textContent
            } else tvContent.text = listItem.contentText

            itemView.setOnClickListener(){
                Toast.makeText(context, "Pressed: ${listItem.title}", Toast.LENGTH_SHORT).show()

                val intent = Intent(context, ContentActivity::class.java).apply {
                    putExtra("image", listItem.imageId)
                    putExtra("title", tvTitle.text.toString())
                    putExtra("content", listItem.contentText)
                }
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextR)
        return ViewHolder(inflater.inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = listR.get(position)
        holder.bind(listItem, contextR)
    }

    override fun getItemCount(): Int {
        return listR.size
    }

    fun updateAdapter(list: List<ListItem>) {
        listR.clear()
        listR.addAll(list)
        notifyDataSetChanged()
    }
}