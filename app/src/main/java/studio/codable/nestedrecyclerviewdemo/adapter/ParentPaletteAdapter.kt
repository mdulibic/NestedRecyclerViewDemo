package studio.codable.nestedrecyclerviewdemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem.ColorListView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem.HeaderView
import studio.codable.nestedrecyclerviewdemo.databinding.ItemHeaderBinding
import studio.codable.nestedrecyclerviewdemo.databinding.LayoutColorItemListBinding

class ParentPaletteAdapter(
    private var childItems: List<ViewTypeItem> = arrayListOf()
) : RecyclerView.Adapter<VH>() {

    companion object {
        const val VIEW_TYPE_CHILD_ITEM = 12
        const val VIEW_TYPE_HEADER = 13
        const val LOG_TAG = "ParentPaletteAdapter"
    }

    fun update(newItems: List<ViewTypeItem>) {
        Log.d(LOG_TAG, "Items: $newItems")
        childItems = newItems
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = childItems[position].viewType

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
                    )
                )
            }
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = childItems[position]

        when (holder) {
            is VH.ColorListVH -> holder.bind(item as ColorListView)
            is VH.HeaderVH -> holder.bind(item as HeaderView)
        }
    }

    override fun getItemCount(): Int = childItems.size
}