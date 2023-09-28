package com.jvcoding.tab_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jvcoding.tab_app.R

class MyPager2Adapter(
    private var componentsList: List<Int>,
): RecyclerView.Adapter<MyPager2Adapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val itemTitle: TextView = itemView.findViewById(R.id.textViewSlider_Slider01)

        fun onBindingElement(title: String) {
            itemTitle.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_slider_one, parent, false))
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.onBindingElement(title = "Slider 0${position+1}")
    }

    override fun getItemCount(): Int {
        return componentsList.size
    }

    enum class SLIDER_POSITION(value: Int) {
        ONE(0),
        TWO(1),
        THREE(2)
    }
}