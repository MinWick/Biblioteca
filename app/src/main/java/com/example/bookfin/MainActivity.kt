package com.example.bookfin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listBook = emptyList<Book>()

        val database = AppDatabase.getDatabase(this)

        database.books().getAll().observe(this, Observer {
            listBook = it

            val adapter = BookAdapter(this, listBook)

            list.adapter = adapter

        })

        list.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, BookActivity::class.java)
            intent.putExtra("id", listBook[position].idBook)
            startActivity(intent)
        }
        floatingActionButton.setOnClickListener{
            val intent = Intent (this, NuevoPrestamoActivity::class.java)
            startActivity(intent)
        }
    }
}