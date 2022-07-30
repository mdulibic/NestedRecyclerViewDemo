package studio.codable.nestedrecyclerviewdemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem.*
import studio.codable.nestedrecyclerviewdemo.adapter.viewHolder.ColorListVH
import studio.codable.nestedrecyclerviewdemo.adapter.viewHolder.HeaderVH
import studio.codable.nestedrecyclerviewdemo.databinding.ItemHeaderBinding
import studio.codable.nestedrecyclerviewdemo.databinding.LayoutColorItemListBinding

class ParentPaletteAdapter(
    private var childItems: ArrayList<ViewTypeItem> = arrayListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_CHILD_ITEM = 12
        const val VIEW_TYPE_HEADER = 13
    }

    fun update(newItems: List<ViewTypeItem>) {
        Log.d("TAG","Items: $newItems")
        childItems.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = childItems[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        Log.d("TAG","View type $viewType")

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
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = childItems[position]

        when (holder) {
            is ColorListVH -> {
                holder.bind((item as ColorListView).colors)
            }
            is HeaderVH -> {
                holder.bind((item as HeaderView).title)
            }
        }
    }

    override fun getItemCount(): Int = childItems.size
}