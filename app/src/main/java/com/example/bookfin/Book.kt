package com.example.bookfin


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Books")


class Book (val nombre_pretamista:String,
            val nombre_libro:String,
            val autor:String,
            val fecha_prestamo:String,
            val fecha_entrega:String,
            val numero_telefono:String,
            @PrimaryKey(autoGenerate = true)
            var idBook: Int = 0
            ) : Serializable