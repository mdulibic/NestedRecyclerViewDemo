package studio.codable.nestedrecyclerviewdemo.adapter.viewHolder

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.ChildColorsAdapter
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.databinding.LayoutColorItemListBinding

class ColorListVH (
    private val binding: LayoutColorItemListBinding,
    private val parentRecycledViewPool: RecyclerView.RecycledViewPool
) : RecyclerView.ViewHolder(binding.root) {

    private val childColorsAdapter = ChildColorsAdapter()
    private val layoutManager = LinearLayoutManager(binding.root.context).apply {
        orientation = LinearLayoutManager.HORIZONTAL
    }

    private var itemDecoration = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View,
            parent: RecyclerView, state: RecyclerView.State
        ) {
                outRect.left = 16
        }
    }

    fun bind(items: List<ColorItem>) {
            binding.rvChildItems.apply {
                layoutManager = this@ColorListVH.layoutManager
                adapter = childColorsAdapter
                setRecycledViewPool(parentRecycledViewPool)
                removeItemDecoration(itemDecoration)
                addItemDecoration(itemDecoration)
                isNestedScrollingEnabled = false
            }
        childColorsAdapter.update(items)
    }
}