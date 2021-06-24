package galstyan.hayk.text.ui.documentList

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


fun createItemTouchHelper() = ItemTouchHelper(
    object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP
                or ItemTouchHelper.DOWN
                or ItemTouchHelper.START
                or ItemTouchHelper.END,
        0
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) = Unit
    }
)
