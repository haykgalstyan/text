package galstyan.hayk.text.ui

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class BoundViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(it: T)
}

abstract class ClickableBoundViewHolder<T>(
    view: View,
    onClick: ((Int) -> Unit),
) : BoundViewHolder<T>(view) {
    init {
        view.rootView.setOnClickListener { onClick.invoke(adapterPosition) }
    }
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


class MarginItemDecoration(
    private val left: Int = 0,
    private val top: Int = 0,
    private val right: Int = 0,
    private val bottom: Int = 0
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(left, top, right, bottom)
    }
}

class MarginFirstItemDecoration(
    private val left: Int = 0,
    private val top: Int = 0,
    private val right: Int = 0,
    private val bottom: Int = 0
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        if (position == 0)
            outRect.set(left, top, right, bottom)
        else super.getItemOffsets(outRect, view, parent, state)
    }
}

class MarginLastItemDecoration(
    private val left: Int = 0,
    private val top: Int = 0,
    private val right: Int = 0,
    private val bottom: Int = 0
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        if (position == state.itemCount - 1)
            outRect.set(left, top, right, bottom)
        else super.getItemOffsets(outRect, view, parent, state)
    }
}


fun RecyclerView.addItemMargins(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) = this.addItemDecoration(MarginItemDecoration(left, top, right, bottom))

fun RecyclerView.addItemMarginsFirst(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) = this.addItemDecoration(MarginFirstItemDecoration(left, top, right, bottom))

fun RecyclerView.addItemMarginsLast(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) = this.addItemDecoration(MarginLastItemDecoration(left, top, right, bottom))


