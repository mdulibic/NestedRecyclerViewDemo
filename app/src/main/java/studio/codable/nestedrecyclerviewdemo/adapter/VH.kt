package studio.codable.nestedrecyclerviewdemo.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.adapter.scrollStateRecovery.ScrollStateRecoveryViewHolder
import studio.codable.nestedrecyclerviewdemo.databinding.ItemHeaderBinding
import studio.codable.nestedrecyclerviewdemo.databinding.LayoutColorItemListBinding

sealed class VH(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class ColorListVH(
        private val binding: LayoutColorItemListBinding,
        private val parentRecycledViewPool: RecyclerView.RecycledViewPool
    ) : VH(binding), ScrollStateRecoveryViewHolder {

        private val childColorsAdapter = ChildColorsAdapter()
        private val linearLayoutManager = LinearLayoutManager(binding.root.context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }

        init {
            binding.rvChildItems.apply {
                layoutManager = linearLayoutManager
                adapter = childColorsAdapter
            }
        }

        private var itemDecoration = object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect, view: View,
                parent: RecyclerView, state: RecyclerView.State
            ) {
                outRect.left = 16
            }
        }

        private lateinit var item: ViewTypeItem.PaletteView

        fun bind(item: ViewTypeItem.PaletteView) {
            val isReused = ::item.isInitialized

            this.item = item

            binding.rvChildItems.apply {
                setRecycledViewPool(parentRecycledViewPool)

                removeItemDecoration(itemDecoration)
                addItemDecoration(itemDecoration)
            }

            if (isReused) {
                childColorsAdapter.forceUpdate(item.palette.colors)
            } else {
                childColorsAdapter.update(item.palette.colors)
            }
        }

        override fun getId(): Int {
            return item.palette.id
        }

        override fun getLayoutManager(): RecyclerView.LayoutManager? {
            return binding.rvChildItems.layoutManager
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