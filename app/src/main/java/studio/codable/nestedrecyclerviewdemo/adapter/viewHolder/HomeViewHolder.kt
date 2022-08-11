package studio.codable.nestedrecyclerviewdemo.adapter.viewHolder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface HomeViewHolder {

    @Parcelize
    data class State(val layoutManagerParcelable: Parcelable?, val contentHashCode: Int): Parcelable

    fun onSaveInstanceState(): State?

    fun restoreState(state: State)
}