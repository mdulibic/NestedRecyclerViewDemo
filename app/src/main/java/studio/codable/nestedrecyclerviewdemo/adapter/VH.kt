package studio.codable.nestedrecyclerviewdemo.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.databinding.ItemHeaderBinding
import studio.codable.nestedrecyclerviewdemo.databinding.LayoutColorItemListBinding

sealed class VH(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class ColorListVH(
        private val binding: LayoutColorItemListBinding,
        private val parentRecycledViewPool: RecyclerView.RecycledViewPool
    ) : VH(binding) {

        private val childColorsAdapter = ChildColorsAdapter()
        private val linearLayoutManager = LinearLayoutManager(binding.root.context).apply {
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

        fun bind(item: ViewTypeItem.ColorListView) {
            binding.rvChildItems.apply {
                layoutManager = linearLayoutManager
                adapter = childColorsAdapter

                setRecycledViewPool(parentRecycledViewPool)

                removeItemDecoration(itemDecoration)
                addItemDecoration(itemDecoration)
            }
            childColorsAdapter.update(item.colors)
        }
    }

    class HeaderVH(
        private val binding: ItemHeaderBinding
    ) : VH(binding) {

        fun bind(headerItem: ViewTypeItem.HeaderView) {
            binding.apply {
                tvHeader.text = headerItem.title
            }
        }
    }
}