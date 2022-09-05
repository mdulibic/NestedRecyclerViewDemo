package studio.codable.nestedrecyclerviewdemo.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView

interface HomeViewHolder {
    fun getId(): Int
    fun getLayoutManager(): RecyclerView.LayoutManager?
}