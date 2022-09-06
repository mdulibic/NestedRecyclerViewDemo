package studio.codable.nestedrecyclerviewdemo.adapter.scrollStateRecovery

import androidx.recyclerview.widget.RecyclerView

interface ScrollStateRecoveryViewHolder {
    fun getId(): Int
    fun getLayoutManager(): RecyclerView.LayoutManager?
}