package com.example.groupdelete



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class MainAdapter(private val dataset: MutableList<String>) :
    RecyclerView.Adapter<MainAdapter.MAinViewHolder>() {

    private var removedPosition: Int = 0
    private var removedItem: String = ""

    class MAinViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(viewHolder: ViewGroup, viewType: Int): MAinViewHolder {
        val v =
            LayoutInflater.from(viewHolder.context).inflate(R.layout.list_item, viewHolder, false)
        return MAinViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: MAinViewHolder, position: Int) {
        viewHolder.title.text = dataset[position]
    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder) {
        removedPosition = viewHolder.adapterPosition
        removedItem = dataset[viewHolder.adapterPosition]

        dataset.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)

        Snackbar.make(viewHolder.itemView, "$removedItem deleted", Snackbar.LENGTH_LONG)
            .setAction("UNDO") {
                dataset.add(removedPosition, removedItem)
                notifyItemInserted(removedPosition)
            }.show()
    }


    override fun getItemCount() = dataset.size
}
