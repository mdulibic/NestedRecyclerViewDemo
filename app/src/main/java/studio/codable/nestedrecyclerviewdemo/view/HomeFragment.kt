package studio.codable.nestedrecyclerviewdemo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import studio.codable.nestedrecyclerviewdemo.HomeViewModel
import studio.codable.nestedrecyclerviewdemo.R
import studio.codable.nestedrecyclerviewdemo.adapter.ParentPaletteAdapter
import studio.codable.nestedrecyclerviewdemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var parentPaletteAdapter: ParentPaletteAdapter

    private val vm: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeLiveData()
        vm.getPaletteList()
    }

    private fun observeLiveData() {
        vm.paletteListHomeData.observe(viewLifecycleOwner) {
            parentPaletteAdapter.update(it)
        }
    }

    private fun initRecyclerView() {
        parentPaletteAdapter = ParentPaletteAdapter()
        binding.rvParent.apply {
            /**
             * === Optimize performance ===
             * 5. Setting RecyclerView’s itemViewCacheSize
             * Set the number of offscreen views to retain before adding them to the
             * shared recycled view pool.
             * The offscreen view cache stays aware of changes in the attached adapter,
             * allowing a LayoutManager to reuse those views unmodified without needing
             * to return to the adapter to rebind them.
             */
            setItemViewCacheSize(30)
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                /**
                 * === Optimize performance ===
                 * 4. Setting the RecyclerView.LayoutManager flag recycleChildrenOnDetach to true
                 * When using a shared RecyclerView.RecycledViewPool,
                 * it might be a good idea to set the flag recycleChildrenOnDetach to true
                 * so that views will be available to other RecyclerViews immediately.
                 */
                recycleChildrenOnDetach = true
            }
            adapter = parentPaletteAdapter
        }
    }
}