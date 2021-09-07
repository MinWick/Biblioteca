package com.example.bookfin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_book.view.*

class BookAdapter (private val mContext :Context, private val listBook: List<Book>): ArrayAdapter<Book>(mContext, 0, listBook) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_book, parent, false)

        val book = listBook[position]


        layout.nombre.text = book.nombre_pretamista
        layout.libro.text = book.nombre_libro
        layout.autor.text= book.autor
        // layout.fecha_entrega.text = book.fecha_entrega
        //layout.fecha_prestamo.text = book.fecha_prestamo
        //  layout.numero.text = book.numero_telefono

        return layout
    }

}