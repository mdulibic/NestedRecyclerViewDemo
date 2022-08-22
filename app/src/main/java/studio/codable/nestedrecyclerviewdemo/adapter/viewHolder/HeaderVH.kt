package studio.codable.nestedrecyclerviewdemo.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.databinding.ItemHeaderBinding

class HeaderVH(
    private val binding: ItemHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(headerItem: ViewTypeItem.HeaderView) {
        binding.apply {
            tvHeader.text = headerItem.title
        }
    }
}