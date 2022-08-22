package studio.codable.nestedrecyclerviewdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.model.Palette

class HomeViewModel : ViewModel() {

    private val _paletteListHomeData = MutableLiveData<List<ViewTypeItem>>()
    val paletteListHomeData: LiveData<List<ViewTypeItem>>
        get() = _paletteListHomeData

    fun getPaletteList() {
        _paletteListHomeData.value = getTestData().toViewTypeItemList()
    }

    private fun List<Palette>.toViewTypeItemList(): List<ViewTypeItem> {
        val listItems: ArrayList<ViewTypeItem> = arrayListOf()
        this.forEach {
            listItems.add(ViewTypeItem.HeaderView(title = it.name))
            listItems.add(ViewTypeItem.ColorListView(colorItems = it.colorItems))
        }
        return listItems
    }
}