package com.example.pasteleria10

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MisPedidosActivity : AppCompatActivity() {

    private lateinit var info: TextView
    private var pastel: Pastel = Pastel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        info = findViewById(R.id.txtInfo)

        val infoRecibida = intent.extras
        pastel.nombre = infoRecibida?.getString("nombre")!!
        pastel.domicilio = infoRecibida?.getString("domicilio")!!
        pastel.tamaño = infoRecibida?.getString("tamaño")!!
        pastel.telefono = infoRecibida?.getString("telefono")!!
        pastel.producto = infoRecibida?.getString("producto")!!

        info.text = "Infomación del pedido:\n" +
                "Nombre: ${pastel.nombre}\n" +
                "Domicilio: ${pastel.domicilio}\n" +
                "Producto: ${pastel.producto}\n" +
                "Tamaño: ${pastel.tamaño}\n" +
                "Teléfono: ${pastel.telefono}"
    }
}