package studio.codable.nestedrecyclerviewdemo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.databinding.ItemColorBinding
import studio.codable.nestedrecyclerviewdemo.model.ColorItem

class ChildColorsAdapter(
    private var colorItemList: List<ColorItem> = listOf()
) : RecyclerView.Adapter<ChildColorsAdapter.ChildColorsVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildColorsVH {
        val binding =
            ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildColorsVH(binding)
    }

    override fun onBindViewHolder(holder: ChildColorsVH, position: Int) {
        return holder.bind(colorItemList[position])
    }

    override fun getItemCount(): Int = colorItemList.size

    fun setChildItems(items: List<ColorItem>) {
        colorItemList = items
        notifyDataSetChanged()
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