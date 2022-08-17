package studio.codable.nestedrecyclerviewdemo.view

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import studio.codable.nestedrecyclerviewdemo.HomeViewModel
import studio.codable.nestedrecyclerviewdemo.R
import studio.codable.nestedrecyclerviewdemo.adapter.ParentPaletteAdapter
import studio.codable.nestedrecyclerviewdemo.adapter.viewHolder.HomeViewHolder
import studio.codable.nestedrecyclerviewdemo.databinding.FragmentHomeBinding
import studio.codable.nestedrecyclerviewdemo.replaceFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var parentPaletteAdapter: ParentPaletteAdapter

    private val vm: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeLiveData()
        onClickListener()
        vm.getPaletteList()
    }

    private fun onClickListener() {
        binding.btnSwitchFragment.setOnClickListener {
            (requireActivity() as AppCompatActivity).apply {
                replaceFragment(R.id.fragment_container_view, SecondFragment.getInstance())
            }
        }
    }

    private var savedState: Bundle? = null

    override fun onPause() {
        super.onPause()

        val stateBundle = Bundle()

        binding.rvParent.children.forEachIndexed { _, view ->
            (binding.rvParent.getChildViewHolder(view) as? HomeViewHolder)?.let { homeVh ->
                val childState = homeVh.onSaveInstanceState()
                childState?.let {
                    stateBundle.putParcelable(
                        it.contentHashCode.toString(),
                        it.layoutManagerParcelable
                    )
                } ?: Log.w(TAG, "$homeVh didn't return child State")
            } ?: Log.w(TAG, "$view does not implement HomeViewHolder")
        }

        savedState = Bundle()
        savedState?.putBundle(CHILD_STATES, stateBundle)
    }

    override fun onResume() {
        super.onResume()

        vm.getPaletteList()

        val childViewHolders =
            binding.rvParent.children.mapNotNull { binding.rvParent.getChildViewHolder(it) as? HomeViewHolder }

        savedState?.let { bundle ->
            val childStates = bundle.get(CHILD_STATES) as? Bundle

            childStates?.keySet()?.forEach { key ->
                val childState = HomeViewHolder.State(childStates[key] as Parcelable?, key.toInt())

                childViewHolders.forEach { child ->
                    Log.w(TAG, "restore $child")
                    child.restoreState(childState)
                }
            }
        }

        savedState = null
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
            setItemViewCacheSize(ITEM_VIEW_CACHE_SIZE)
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                /**
                 * === Optimize performance ===
                 * 2. Setting the RecyclerView.LayoutManager flag recycleChildrenOnDetach to true
                 * When using a shared RecyclerView.RecycledViewPool,
                 * it might be a good idea to set the flag recycleChildrenOnDetach to true
                 * so that views will be available to other RecyclerViews immediately.
                 */
                recycleChildrenOnDetach = true
            }
            adapter = parentPaletteAdapter
        }
    }

    companion object {
        const val ITEM_VIEW_CACHE_SIZE = 30
        const val TAG = "HomeFragment"
        const val CHILD_STATES = "child_states"
    }
}