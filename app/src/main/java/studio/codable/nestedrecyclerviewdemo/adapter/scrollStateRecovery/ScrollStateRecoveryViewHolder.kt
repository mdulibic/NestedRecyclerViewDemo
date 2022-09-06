package studio.codable.nestedrecyclerviewdemo.adapter.scrollStateRecovery

import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.Parcelize

interface ScrollStateRecoveryViewHolder {

    @Parcelize
    data class State(val contentId: Int, val layoutManagerParcelable: Parcelable?): Parcelable

    fun onSaveInstanceState(): State?

    fun restoreState(state: State)

    fun getId(): Int
    fun getLayoutManager(): RecyclerView.LayoutManager?
}