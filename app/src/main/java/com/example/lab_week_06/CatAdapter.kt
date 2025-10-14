package com.example.lab_week_06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel

class CatAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<CatViewHolder>() {

    // Mutable list untuk menyimpan semua data kucing
    private val cats = mutableListOf<CatModel>()

    // Fungsi untuk mengganti seluruh isi list data
    fun setData(newCats: List<CatModel>) {
        cats.clear()
        cats.addAll(newCats)
        // Memberi tahu adapter bahwa datanya berubah, supaya tampilan diperbarui
        notifyDataSetChanged()
    }

    // Membuat ViewHolder baru (meng-inflate layout item_list.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return CatViewHolder(view, imageLoader)
    }

    // Menghitung jumlah item di RecyclerView
    override fun getItemCount() = cats.size

    // Mengikat data ke tiap item di RecyclerView
    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bindData(cats[position])
    }
}
