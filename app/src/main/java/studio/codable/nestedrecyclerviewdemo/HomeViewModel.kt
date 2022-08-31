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
        return arrayListOf<ViewTypeItem>().also {
            this.forEach { palette ->
                it.add(ViewTypeItem.HeaderView(title = palette.name))
                it.add(ViewTypeItem.ColorListView(colorItems = palette.colorItems))
            }
        }
    }
}