package com.example.pasteleria10

import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductosActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val productos = arrayOf(
        "Pastel de Chocolate",
        "Pastel de Fresa",
        "Pastel de Café",
        "Pastel Napolitano",
        "Pastel de Tres Leches"
    )

    private val detallesProductos = mapOf(
        "Pastel de Chocolate" to "Descripción: Pastel de chocolate puro.\nTallas: Chico, Mediano, Familiar.\nCosto: $150 - $300",
        "Pastel de Fresa" to "Descripción: Pastel con fresas frescas.\nTallas: Chico, Mediano, Familiar.\nCosto: $160 - $320",
        "Pastel de Café" to "Descripción: Pastel con sabor a café.\nTallas: Chico, Mediano, Familiar.\nCosto: $140 - $280",
        "Pastel Napolitano" to "Descripción: Pastel con tres sabores (vainilla, fresa y chocolate).\nTallas: Chico, Mediano, Familiar.\nCosto: $170 - $340",
        "Pastel de Tres Leches" to "Descripción: Pastel tradicional de tres leches.\nTallas: Chico, Mediano, Familiar.\nCosto: $180 - $360"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        listView = findViewById(R.id.lstProductos)

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, productos)
        listView.adapter = adaptador

        listView.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            val productoSeleccionado = productos[position]

            val detalles = detallesProductos[productoSeleccionado]
            Toast.makeText(this, detalles, Toast.LENGTH_LONG).show()
        }
    }
}