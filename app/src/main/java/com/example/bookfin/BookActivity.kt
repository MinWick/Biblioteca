package com.example.bookfin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase

    private lateinit var book: Book

    private lateinit var bookLiveData: LiveData<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        database = AppDatabase.getDatabase(this)

        val idBook = intent.getIntExtra("id", 0 )

        bookLiveData = database.books().get(idBook)

        bookLiveData.observe(this, Observer {
            book = it

            nombre_pretamista.text = book.nombre_pretamista
            libro_prestado.text = book.nombre_libro
            autor_libro.text = book.autor
            prestamo.text = book.fecha_prestamo
            entrega.text = book.fecha_entrega
            numero_prestamista.text = book.numero_telefono
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.prestamo_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.edit -> {
            val intent = Intent(this, NuevoPrestamoActivity::class.java)
                //intent.PutExtra("book", book)
                intent.putExtra("book", book)
                startActivity(intent)
            }
            R.id.delete ->{
           // bookLiveData.removeObserver(this)
                bookLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.books().delete(book)
                    this@BookActivity.finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}