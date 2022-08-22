package studio.codable.nestedrecyclerviewdemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem.ColorListView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem.HeaderView
import studio.codable.nestedrecyclerviewdemo.adapter.viewHolder.ColorListVH
import studio.codable.nestedrecyclerviewdemo.adapter.viewHolder.HeaderVH
import studio.codable.nestedrecyclerviewdemo.databinding.ItemHeaderBinding
import studio.codable.nestedrecyclerviewdemo.databinding.LayoutColorItemListBinding

class ParentPaletteAdapter(
    private var childItems: List<ViewTypeItem> = arrayListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_CHILD_ITEM = 12
        const val VIEW_TYPE_HEADER = 13
        const val PARENT_PALETTE_ADAPTER = "ParentPaletteAdapter"
    }

    fun update(newItems: List<ViewTypeItem>) {
        Log.d(PARENT_PALETTE_ADAPTER, "Items: $newItems")
        childItems = newItems
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = childItems[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        Log.d(PARENT_PALETTE_ADAPTER, "View type $viewType")

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
                    )
                )
            }
            else -> throw IllegalStateException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = childItems[position]

        when (holder) {
            is ColorListVH -> holder.bind(item as ColorListView)
            is HeaderVH -> holder.bind(item as HeaderView)
        }
    }

    override fun getItemCount(): Int = childItems.size
}