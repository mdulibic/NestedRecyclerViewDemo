package studio.codable.nestedrecyclerviewdemo

import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.model.Palette

fun List<Palette>.toViewTypeItemList(): List<ViewTypeItem> {
    val listItems: ArrayList<ViewTypeItem> = arrayListOf()
    this.forEach {
        listItems.add(ViewTypeItem.HeaderView(title = it.name))
        listItems.add(ViewTypeItem.ColorListView(colors = it.colors))
    }
    return listItems
}