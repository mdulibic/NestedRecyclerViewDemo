package studio.codable.nestedrecyclerviewdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem

class HomeViewModel: ViewModel() {

    private val _paletteListHomeData = MutableLiveData<List<ViewTypeItem>>()
    val paletteListHomeData: LiveData<List<ViewTypeItem>>
        get() = _paletteListHomeData

    fun getPaletteList(){
        _paletteListHomeData.value = getTestData().toViewTypeItemList()
    }

}