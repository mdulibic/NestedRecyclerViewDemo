package studio.codable.nestedrecyclerviewdemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem.HeaderView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem.PaletteView
import studio.codable.nestedrecyclerviewdemo.databinding.ItemHeaderBinding
import studio.codable.nestedrecyclerviewdemo.databinding.LayoutColorItemListBinding

class ParentPaletteAdapter : ScrollStateRecoveryAdapter<ViewTypeItem, VH>(DiffUtilViewTypeItem()) {

    /**
     * === Optimize performance ===
     * 3. Implement DiffUtil
     * Improve RecyclerView’s performance when handling list updates.
     */

    private class DiffUtilViewTypeItem : DiffUtil.ItemCallback<ViewTypeItem>() {
        override fun areItemsTheSame(oldItem: ViewTypeItem, newItem: ViewTypeItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ViewTypeItem, newItem: ViewTypeItem): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * === Optimize performance ===
     * 1. Setting a Single view pool for all the nested RecyclerView Pools
     * Decrease the view creation time and make the scrolling experience
     * smoother for the user.
     */

    private val parentRecycledViewPool = RecyclerView.RecycledViewPool()

    companion object {
        const val VIEW_TYPE_CHILD_ITEM = 12
        const val VIEW_TYPE_HEADER = 13
        const val LOG_TAG = "ParentPaletteAdapter"
    }

    override fun getItemViewType(position: Int): Int = getItem(position).viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val context = parent.context
        Log.d(LOG_TAG, "View type $viewType")

        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                VH.HeaderVH(
                    ItemHeaderBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_CHILD_ITEM -> {
                VH.ColorListVH(
                    LayoutColorItemListBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    ),
                    parentRecycledViewPool
                )
            }
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)

        when (holder) {
            is VH.ColorListVH -> holder.bind(item as PaletteView)
            is VH.HeaderVH -> holder.bind(item as HeaderView)
        }
        super.onBindViewHolder(holder, position)
    }
}