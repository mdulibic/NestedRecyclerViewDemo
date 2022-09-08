package studio.codable.nestedrecyclerviewdemo.adapter.model

import studio.codable.nestedrecyclerviewdemo.adapter.ParentPaletteAdapter
import studio.codable.nestedrecyclerviewdemo.model.ColorItem
import studio.codable.nestedrecyclerviewdemo.model.Palette

sealed class ViewTypeItem {

    abstract val viewType: Int

    data class PaletteView(val palette: Palette) : ViewTypeItem() {
        override val viewType: Int = ParentPaletteAdapter.VIEW_TYPE_CHILD_ITEM
    }

    data class HeaderView(val title: String) :
        ViewTypeItem() {
        override val viewType: Int = ParentPaletteAdapter.VIEW_TYPE_HEADER
    }

}