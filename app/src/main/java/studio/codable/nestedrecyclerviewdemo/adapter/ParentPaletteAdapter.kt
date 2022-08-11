package studio.codable.nestedrecyclerviewdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem.*
import studio.codable.nestedrecyclerviewdemo.adapter.viewHolder.ColorListVH
import studio.codable.nestedrecyclerviewdemo.adapter.viewHolder.HeaderVH
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
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ViewTypeItem, newItem: ViewTypeItem): Boolean {
            return oldItem == newItem
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
    }

    fun update(newItems: List<ViewTypeItem>) {
        differ.submitList(newItems)
    }

    override fun getItemViewType(position: Int): Int = differ.currentList[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context

        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                HeaderVH(
                    ItemHeaderBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_CHILD_ITEM -> {
                ColorListVH(
                    LayoutColorItemListBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    ),
                    parentRecycledViewPool
                )
            }
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = differ.currentList[position]

        when (holder) {
            is ColorListVH -> {
                holder.bind(item as ColorListView)
            }
            is HeaderVH -> {
                holder.bind((item as HeaderView).title)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}