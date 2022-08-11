package studio.codable.nestedrecyclerviewdemo.adapter.model

import studio.codable.nestedrecyclerviewdemo.adapter.ParentPaletteAdapter
import studio.codable.nestedrecyclerviewdemo.model.ColorItem

interface ScrollPositionRemembering {
    var scrollPosition: Int
}

sealed class ViewTypeItem {

    abstract val viewType: Int

    data class ColorListView(val colors: List<ColorItem>) : ViewTypeItem(), ScrollPositionRemembering {
        override val viewType: Int = ParentPaletteAdapter.VIEW_TYPE_CHILD_ITEM
        override var scrollPosition: Int = 0
    }

    data class HeaderView(val title: String) :
        ViewTypeItem() {
        override val viewType: Int = ParentPaletteAdapter.VIEW_TYPE_HEADER
    }

}