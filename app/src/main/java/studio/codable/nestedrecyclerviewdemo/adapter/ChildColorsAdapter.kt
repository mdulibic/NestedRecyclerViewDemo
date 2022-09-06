package studio.codable.nestedrecyclerviewdemo.adapter

import android.graphics.Color
import android.graphics.Color.parseColor
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.databinding.ItemColorBinding
import studio.codable.nestedrecyclerviewdemo.model.ColorItem

class ChildColorsAdapter :
    RecyclerView.Adapter<ChildColorsAdapter.ChildColorsVH>() {

    private lateinit var colors: List<ColorItem>

    companion object {
        const val LOG_TAG = "ChildColorsAdapter"
    }

    private val differCallback = object : DiffUtil.ItemCallback<ColorItem>() {
        override fun areItemsTheSame(oldItem: ColorItem, newItem: ColorItem): Boolean {
            Log.d(
                LOG_TAG,
                "areItemsTheSame: ${oldItem.id == newItem.id} -> ${oldItem.id} == ${newItem.id}"
            )
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ColorItem, newItem: ColorItem): Boolean {
            Log.d(
                LOG_TAG,
                "areContentsTheSame: ${oldItem == newItem} -> $oldItem == $newItem"
            )
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

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
        return colors[position].id.toLong()
    }

    fun update(newItems: List<ColorItem>) {
        differ.submitList(newItems)
        colors = newItems
    }

    fun forceUpdate(newItems: List<ColorItem>) {
        colors = newItems
        notifyDataSetChanged()
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
        return holder.bind(colors[position])
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

    override fun getItemCount(): Int = colors.size
}