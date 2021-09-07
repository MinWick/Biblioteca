
package com.example.bookfin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nuevo_prestamo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoPrestamoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_prestamo)

        var idBook: Int? = null

        if (intent.hasExtra("book")) {
            val book = intent.extras?.getSerializable("book") as Book

            name.setText(book.nombre_pretamista)
            autor_et.setText(book.autor)
            nombre_libro_et.setText(book.nombre_libro)
            fecha_entrega_et.setText(book.fecha_entrega)
            fecha_prestamo_et.setText(book.fecha_prestamo)
            numero_et.setText(book.numero_telefono)
            idBook = book.idBook

        }

        val database = AppDatabase.getDatabase(this)


            guardar.setOnClickListener{
            val nombre = name.text.toString()
            val autor = autor_et.text.toString()
            val nombre_libro = nombre_libro_et.text.toString()
            val fecha_entrega = fecha_entrega_et.text.toString()
            val fecha_prestamo = fecha_prestamo_et.text.toString()
            val numero = numero_et.text.toString()

            val book = Book(nombre, autor, nombre_libro, fecha_entrega, fecha_prestamo, numero)

                if (idBook != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        book.idBook = idBook

                        database.books().update(book)

                        this@NuevoPrestamoActivity.finish()
                    }
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        database.books().insertAll(book)

                        this@NuevoPrestamoActivity.finish()
                    }
                }
            }
        }
    }