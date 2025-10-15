package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    // Ambil referensi RecyclerView dari layout
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    // Adapter dengan image loader dan listener klik
    private val catAdapter by lazy {
        CatAdapter(
            layoutInflater,
            GlideImageLoader(this),
            object : CatAdapter.OnClickListener {
                override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pasang adapter ke RecyclerView
        recyclerView.adapter = catAdapter

        // Pasang layout manager (mengatur arah dan struktur list)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // 🔥 Tambahkan gesture swipe ke RecyclerView
        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // Tambahkan data contoh
        catAdapter.setData(
            listOf(
                CatModel(
                    Gender.Male,
                    CatBreed.BalineseJavanese,
                    "Fred",
                    "Silent and deadly",
                    "https://cdn2.thecatapi.com/images/7dj.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.ExoticShorthair,
                    "Wilma",
                    "Cuddly assassin",
                    "https://cdn2.thecatapi.com/images/egv.jpg"
                ),
                CatModel(
                    Gender.Unknown,
                    CatBreed.AmericanCurl,
                    "Curious George",
                    "Award winning investigator",
                    "https://cdn2.thecatapi.com/images/bar.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.Abyssinian,
                    "Luna",
                    "Elegant and curious",
                    "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.MaineCoon,
                    "Leo",
                    "The gentle giant",
                    "https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.Bengal,
                    "Simba",
                    "Active and talkative",
                    "https://cdn2.thecatapi.com/images/O3btzLlsO.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.Sphynx,
                    "Nala",
                    "Loves warm blankets",
                    "https://cdn2.thecatapi.com/images/4RzEwvyZZ.jpg"
                ),
                CatModel(
                    Gender.Unknown,
                    CatBreed.BritishShorthair,
                    "Shadow",
                    "Calm and quiet",
                    "https://cdn2.thecatapi.com/images/s4wQfYoEk.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.Persian,
                    "Oscar",
                    "Always sleepy",
                    "https://cdn2.thecatapi.com/images/LYoYqNfDC.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.Siamese,
                    "Mimi",
                    "Elegant and vocal",
                    "https://cdn2.thecatapi.com/images/ai6Jps4sx.jpg"
                )
            )
        )
    }

    // Menampilkan pop-up dialog ketika item diklik
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}
