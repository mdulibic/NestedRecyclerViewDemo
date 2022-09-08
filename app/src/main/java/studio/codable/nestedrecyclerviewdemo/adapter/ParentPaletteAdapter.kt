package studio.codable.nestedrecyclerviewdemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem.*
import studio.codable.nestedrecyclerviewdemo.databinding.ItemHeaderBinding
import studio.codable.nestedrecyclerviewdemo.databinding.LayoutColorItemListBinding

class ParentPaletteAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * === Optimize performance ===
     * 3. Implement DiffUtil
     * Improve RecyclerView’s performance when handling list updates.
     */
    private val differCallback = object : DiffUtil.ItemCallback<ViewTypeItem>() {
        override fun areItemsTheSame(oldItem: ViewTypeItem, newItem: ViewTypeItem): Boolean {
            when (oldItem) {
                is HeaderView -> {
                    return if (newItem is HeaderView) {
                        oldItem.title == newItem.title
                    } else {
                        false
                    }
                }
                is ColorListView -> {
                    return if (newItem is ColorListView) {
                        oldItem.colorItems == newItem.colorItems
                    } else {
                        false
                    }
                }
            }
        }

        override fun areContentsTheSame(oldItem: ViewTypeItem, newItem: ViewTypeItem): Boolean {
            when (oldItem) {
                is HeaderView -> {
                    return if (newItem is HeaderView) {
                        oldItem.title == newItem.title
                    } else {
                        false
                    }
                }
                is ColorListView -> {
                    return if (newItem is ColorListView) {
                        oldItem.colorItems == newItem.colorItems
                    } else {
                        false
                    }
                }
            }
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

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

    fun update(newItems: List<ViewTypeItem>) {
        Log.d(LOG_TAG, "New items: $newItems")
        differ.submitList(newItems)
    }

    override fun getItemViewType(position: Int): Int = differ.currentList[position].viewType

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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = differ.currentList[position]

        when (holder) {
            is VH.ColorListVH -> holder.bind(item as ColorListView)
            is VH.HeaderVH -> holder.bind(item as HeaderView)
            else -> throw IllegalStateException("Unknown view holder: $holder")
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}