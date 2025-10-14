package com.example.lab_week_06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recycler_view)
    }

    private val catAdapter by lazy {
        // Glide digunakan untuk load gambar
        CatAdapter(layoutInflater, GlideImageLoader(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup adapter untuk RecyclerView
        recyclerView.adapter = catAdapter

        // Setup layout manager agar item tampil vertikal
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        // Tambahkan data kucing ke adapter
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
                )
            )
        )
    }
}
