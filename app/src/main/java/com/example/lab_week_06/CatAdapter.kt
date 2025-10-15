package com.example.lab_week_06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel

// Adapter = penghubung antara data (CatModel) dan tampilan (RecyclerView)
class CatAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<CatViewHolder>() {

    // Data utama yang akan ditampilkan dalam RecyclerView
    private val cats = mutableListOf<CatModel>()

    // Fungsi untuk mengganti seluruh data dalam list
    fun setData(newCats: List<CatModel>) {
        cats.clear()
        cats.addAll(newCats)
        notifyDataSetChanged() // beri tahu adapter kalau data berubah total
    }

    // Fungsi untuk menghapus satu item dari list berdasarkan posisinya
    fun removeItem(position: Int) {
        cats.removeAt(position)
        notifyItemRemoved(position) // beri tahu RecyclerView agar tampilan diperbarui
    }

    // Instansiasi callback untuk fitur swipe
    val swipeToDeleteCallback = SwipeToDeleteCallback()

    // Membuat ViewHolder (satu item layout)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return CatViewHolder(view, imageLoader, onClickListener)
    }

    // Jumlah item dalam list
    override fun getItemCount() = cats.size

    // Menghubungkan data dengan tampilan (dipanggil setiap kali item ditampilkan)
    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bindData(cats[position])
    }

    // Interface agar Activity bisa menangani event klik item
    interface OnClickListener {
        fun onItemClick(cat: CatModel)
    }

    // Inner class untuk gesture swipe (menghapus item)
    inner class SwipeToDeleteCallback :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

        // Tidak mengaktifkan drag (geser posisi item)
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        // Menentukan arah gesture yang diizinkan
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return if (viewHolder is CatViewHolder) {
                makeMovementFlags(
                    ItemTouchHelper.ACTION_STATE_IDLE,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) or makeMovementFlags(
                    ItemTouchHelper.ACTION_STATE_SWIPE,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                )
            } else 0
        }

        // Ketika item diswipe → hapus dari list
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            removeItem(position)
        }
    }
}
