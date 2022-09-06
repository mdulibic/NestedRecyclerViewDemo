package studio.codable.nestedrecyclerviewdemo.adapter.scrollStateRecovery

import android.content.Context
import android.os.Parcelable
import android.util.Log
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScrollStateRecoveryLinearLayoutManager(private val context: Context, private val recyclerView: RecyclerView):
    LinearLayoutManager(context) {

    companion object {
        const val LOG_TAG = "ScrollStateRecoveryLM"
    }

    private val childScrollStates = hashMapOf<Int, Parcelable>()

    override fun onSaveInstanceState(): Parcelable? {
        val state = super.onSaveInstanceState()

        recyclerView.children.forEachIndexed { _, view ->
            ( recyclerView.getChildViewHolder(view) as? ScrollStateRecoveryViewHolder)?.let { vh ->
                val childState = vh.onSaveInstanceState()
                childState?.let {
                    it.layoutManagerParcelable?.let { parcelable ->
                        childScrollStates.put( it.contentId,
                            parcelable
                        )
                    }
                } ?: Log.w(LOG_TAG, "$vh didn't return child State")
            } ?: Log.w(LOG_TAG, "$view does not implement HomeViewHolder")
        }
        return state
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)

        val childViewHolders =
            recyclerView.children.mapNotNull { recyclerView.getChildViewHolder(it) as? ScrollStateRecoveryViewHolder }


            childScrollStates.forEach { (key, value) ->
                val childState = ScrollStateRecoveryViewHolder.State(key, value)

                childViewHolders.forEach { child ->
                    Log.w(LOG_TAG, "Restore $child")
                    child.restoreState(childState)
                }
            }
        }
}