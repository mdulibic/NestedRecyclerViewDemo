package studio.codable.nestedrecyclerviewdemo.adapter.scrollStateRecovery

import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

private data class ViewHolderReference(
    val id: Int,
    val reference: WeakReference<ScrollStateRecoveryViewHolder>
)

abstract class ScrollStateRecoveryAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private val recycledScrollStates = hashMapOf<Int, Parcelable?>()
    private val visibleScrollStates = hashMapOf<Int, ViewHolderReference>()

    override fun onViewRecycled(holder: VH) {
        if (holder is ScrollStateRecoveryViewHolder) {
            val state = holder.getLayoutManager()?.onSaveInstanceState()

            recycledScrollStates[holder.getId()] = state

            visibleScrollStates.remove(holder.hashCode())
        }
        super.onViewRecycled(holder)
    }

   fun saveState() {
        visibleScrollStates.values.forEach { item ->
            item.reference.get()?.let {
                recycledScrollStates[item.id] = it.getLayoutManager()?.onSaveInstanceState()
            }
        }
        visibleScrollStates.clear()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        (holder as? ScrollStateRecoveryViewHolder)?.let {
            holder.getLayoutManager()?.let {
                val state: Parcelable? = recycledScrollStates[holder.getId()]

                if (state != null) {
                    it.onRestoreInstanceState(state)
                } else {
                    it.scrollToPosition(0)
                }
            }

            visibleScrollStates[holder.getId()] = ViewHolderReference(
                holder.getId(),
                WeakReference(holder as ScrollStateRecoveryViewHolder)
            )
        }
    }

}