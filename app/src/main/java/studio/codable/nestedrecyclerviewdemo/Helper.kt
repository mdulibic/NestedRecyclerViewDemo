package studio.codable.nestedrecyclerviewdemo

import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.model.Palette

fun List<Palette>.toViewTypeItemList(): List<ViewTypeItem> {
    val listItems: ArrayList<ViewTypeItem> = arrayListOf()
    this.forEach {
        listItems.add(ViewTypeItem.HeaderView(title = it.name))
        listItems.add(ViewTypeItem.ColorListView(colorItems = it.colorItems))
    }
    return listItems
}