package studio.codable.nestedrecyclerviewdemo.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.databinding.ItemHeaderBinding

class HeaderVH(
    private val binding: ItemHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(header: String) {
        binding.apply {
            tvHeader.text = header
        }
    }
}