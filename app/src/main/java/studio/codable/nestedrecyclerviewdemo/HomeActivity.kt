package studio.codable.nestedrecyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import studio.codable.nestedrecyclerviewdemo.adapter.ParentPaletteAdapter
import studio.codable.nestedrecyclerviewdemo.adapter.model.ViewTypeItem
import studio.codable.nestedrecyclerviewdemo.databinding.ActivityHomeBinding
import studio.codable.nestedrecyclerviewdemo.model.Palette

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    private lateinit var parentPaletteAdapter: ParentPaletteAdapter

    private val vm: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        observeLiveData()
        vm.getPaletteList()
    }

    private fun observeLiveData() {
        vm.paletteListHomeData.observe(this) {
            parentPaletteAdapter.update(it)
        }
    }

    private fun initRecyclerView() {
        parentPaletteAdapter = ParentPaletteAdapter()
        binding.rvParent.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = parentPaletteAdapter
        }
    }
}