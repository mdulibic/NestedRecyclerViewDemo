package studio.codable.nestedrecyclerviewdemo.adapter

import android.os.Parcelable
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import studio.codable.nestedrecyclerviewdemo.adapter.viewHolder.HomeViewHolder
import java.lang.ref.WeakReference

private data class ViewHolderReference(
    val id: Int,
    val reference: WeakReference<HomeViewHolder>
)

abstract class ScrollStateRecoveryAdapter<T, VH : RecyclerView.ViewHolder>(
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffUtil) {

    private val layoutManagerStates = hashMapOf<Int, Parcelable?>()
    private val visibleScrollViews = hashMapOf<Int, ViewHolderReference>()

    @CallSuper
    override fun onViewRecycled(holder: VH) {
        if (holder is HomeViewHolder) {
            val state = holder.getLayoutManager()?.onSaveInstanceState()
            layoutManagerStates[holder.getId()] = state

            // State saved and view is not visible
            visibleScrollViews.remove(holder.hashCode())
        }
        super.onViewRecycled(holder)
    }

    @CallSuper
    override fun submitList(list: List<T>?) {
        // We need to save the state from visible views before updating/setting the list to preserve
        // their states
        saveState()
        super.submitList(list)
    }

    private fun saveState() {
        visibleScrollViews.values.forEach { item ->
            item.reference.get()?.let {
                layoutManagerStates[item.id] = it.getLayoutManager()?.onSaveInstanceState()
            }
        }
        visibleScrollViews.clear()
    }

    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        (holder as? HomeViewHolder)?.let {
            holder.getLayoutManager()?.let {
                val state: Parcelable? = layoutManagerStates[holder.getId()]
                if (state != null) {
                    it.onRestoreInstanceState(state)
                } else {
                    // We need to reset the scroll position when no state needs to be restored
                    it.scrollToPosition(0)
                }
            }

            visibleScrollViews[holder.hashCode()] = ViewHolderReference(
                holder.getId(),
                WeakReference(holder as HomeViewHolder)
            )
        }
    }

}