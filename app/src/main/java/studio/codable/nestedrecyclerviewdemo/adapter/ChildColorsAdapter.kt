package studio.codable.nestedrecyclerviewdemo.adapter

import android.graphics.Color.parseColor
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.databinding.ItemColorBinding
import studio.codable.nestedrecyclerviewdemo.model.ColorItem

class ChildColorsAdapter :
    ListAdapter<ColorItem, ChildColorsAdapter.ChildColorsVH>(DiffUtilColorItem()) {

    /**
     * === Optimize performance ===
     * 4. setHasStableIds(true)
     * True means this adapter would publish a unique value as a key for item in data set.
     * Adapter can use the key to indicate they are the same one or not after notifying
     * data changed.
     * Then we must override getItemId(int position),to return identified long for the
     * item at position
     */

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }


    private class DiffUtilColorItem : DiffUtil.ItemCallback<ColorItem>() {

        override fun areItemsTheSame(oldItem: ColorItem, newItem: ColorItem): Boolean {
            Log.d(
                "ChildColorsAdapter",
                "areItemsTheSame: ${oldItem::class.simpleName == newItem::class.simpleName} -> ${oldItem::class.simpleName} == ${newItem::class.simpleName}"
            )
            return oldItem::class.simpleName == newItem::class.simpleName
        }

        override fun areContentsTheSame(oldItem: ColorItem, newItem: ColorItem): Boolean {
            Log.d(
                "ChildColorsAdapter",
                "areContentsTheSame: ${oldItem.id == newItem.id} -> ${oldItem.id} == ${newItem.id}"
            )
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildColorsVH {
        return ChildColorsVH(
            ItemColorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChildColorsVH, position: Int) {
        return holder.bind(getItem(position))
    }

    inner class ChildColorsVH(private val binding: ItemColorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ColorItem) {
            binding.apply {
                tvName.text = item.name
                bgItem.setCardBackgroundColor(parseColor(item.hexCode))
            }
        }
    }
}