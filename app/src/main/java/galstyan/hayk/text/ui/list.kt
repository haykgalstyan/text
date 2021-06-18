package galstyan.hayk.text.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BoundViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(it: T)
}

abstract class BaseListAdapter<T>(differ: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BoundViewHolder<T>>(differ) {

    abstract fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BoundViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoundViewHolder<T> =
        createViewHolder(LayoutInflater.from(parent.context), parent, viewType)

    override fun onBindViewHolder(holder: BoundViewHolder<T>, position: Int) =
        holder.bind(getItem(position))
}