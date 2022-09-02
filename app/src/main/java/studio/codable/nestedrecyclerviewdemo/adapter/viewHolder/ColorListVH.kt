package studio.codable.nestedrecyclerviewdemo.adapter.viewHolder

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.ChildColorsAdapter
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.databinding.LayoutColorItemListBinding
import studio.codable.nestedrecyclerviewdemo.model.ColorItem

class ColorListVH (
    private val binding: LayoutColorItemListBinding,
    private val parentRecycledViewPool: RecyclerView.RecycledViewPool
) : RecyclerView.ViewHolder(binding.root), HomeViewHolder {

    private val childColorsAdapter = ChildColorsAdapter()

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            item?.let {
                it.scrollPosition += dx
            }
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

    private var item: ViewTypeItem.ColorListView? = null

    fun bind(item: ViewTypeItem.ColorListView) {
        this.item = item

            binding.rvChildItems.apply {
                layoutManager = LinearLayoutManager(binding.root.context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = childColorsAdapter
                setRecycledViewPool(parentRecycledViewPool)

                removeItemDecoration(itemDecoration)
                addItemDecoration(itemDecoration)

                isNestedScrollingEnabled = false

                scrollToPosition(item.scrollPosition)

                removeOnScrollListener(scrollListener)
                addOnScrollListener(scrollListener)
            }
        childColorsAdapter.update(item.colors)
    }

    override fun onSaveInstanceState(): HomeViewHolder.State {
        return HomeViewHolder.State(
            binding.rvChildItems.layoutManager?.onSaveInstanceState(),
            item?.colors.hashCode()
        )
    }

    override fun restoreState(state: HomeViewHolder.State) {
        if (item?.colors.hashCode() == state.contentHashCode) {
            Log.d("ColorListVH","Found a match for $this")
            binding.rvChildItems.layoutManager?.onRestoreInstanceState(state.layoutManagerParcelable)
        }
    }
}