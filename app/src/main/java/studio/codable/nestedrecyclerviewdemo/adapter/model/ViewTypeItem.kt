package studio.codable.nestedrecyclerviewdemo.adapter.model

import studio.codable.nestedrecyclerviewdemo.adapter.ParentPaletteAdapter
import studio.codable.nestedrecyclerviewdemo.model.ColorItem

sealed class ViewTypeItem {

    abstract val viewType: Int

    data class ColorListView(val colorItems: List<ColorItem>) : ViewTypeItem() {
        override val viewType: Int = ParentPaletteAdapter.VIEW_TYPE_CHILD_ITEM
    }

    data class HeaderView(val title: String) :
        ViewTypeItem() {
        override val viewType: Int = ParentPaletteAdapter.VIEW_TYPE_HEADER
    }

}