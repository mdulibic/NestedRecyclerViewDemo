package studio.codable.nestedrecyclerviewdemo.adapter.model

import studio.codable.nestedrecyclerviewdemo.adapter.ParentPaletteAdapter
import studio.codable.nestedrecyclerviewdemo.model.ColorItem
import studio.codable.nestedrecyclerviewdemo.model.Palette

interface ScrollPositionRemembering {
    var scrollPosition: Int
}

sealed class ViewTypeItem {

    abstract val viewType: Int

    data class PaletteView(val palette: Palette) : ViewTypeItem(), ScrollPositionRemembering {
        override val viewType: Int = ParentPaletteAdapter.VIEW_TYPE_CHILD_ITEM
        override var scrollPosition: Int = 0

        override fun hashCode(): Int {
            return palette.id
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as PaletteView

            if (palette != other.palette) return false

            return true
        }
    }

    data class HeaderView(val title: String) :
        ViewTypeItem() {
        override val viewType: Int = ParentPaletteAdapter.VIEW_TYPE_HEADER

        override fun hashCode(): Int {
            return title.hashCode()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as HeaderView

            if (title != other.title) return false

            return true
        }
    }
}