package studio.codable.nestedrecyclerviewdemo.adapter

import android.graphics.Color
import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.databinding.ItemColorBinding
import studio.codable.nestedrecyclerviewdemo.model.ColorItem

class ChildColorsAdapter: RecyclerView.Adapter<ChildColorsAdapter.ChildColorsVH>() {

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
        return differ.currentList[position].id
    }

    private val differCallback = object : DiffUtil.ItemCallback<ColorItem>() {
        override fun areItemsTheSame(oldItem:ColorItem, newItem: ColorItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem:ColorItem, newItem:ColorItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildColorsVH {
        val binding =
            ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildColorsVH(binding)
    }

    override fun onBindViewHolder(holder: ChildColorsVH, position: Int) {
        return holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun update(newItems: List<ColorItem>) {
        differ.submitList(newItems)
    }

    inner class ChildColorsVH(private val binding: ItemColorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ColorItem) {
            binding.apply {
                tvName.text = item.name
                bgItem.setCardBackgroundColor(Color.parseColor(item.hexCode))
            }
            }
        }
}