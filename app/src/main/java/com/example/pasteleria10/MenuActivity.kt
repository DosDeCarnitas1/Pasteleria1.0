package com.example.pasteleria10

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MenuActivity: AppCompatActivity() {

    private lateinit var pastel: Pastel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val toolbar: Toolbar = findViewById(R.id.barra)
        setSupportActionBar(toolbar)

        pastel = Pastel()

        var infoRecibida = intent.extras
        if (infoRecibida != null) {
            pastel.nombre = infoRecibida?.getString("nombre")!!
            pastel.domicilio = infoRecibida?.getString("domicilio")!!
            pastel.tamaño = infoRecibida?.getString("tamaño")!!
            pastel.producto = infoRecibida?.getString("producto")!!
            pastel.telefono = infoRecibida?.getString("telefono")!!
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent?
        when (item.itemId){
            R.id.itmPedidos -> {
                intent = Intent(applicationContext, PedidoActivity::class.java)
                startActivity(intent)
            }
            R.id.itmMisPedidos -> {
                intent = Intent(applicationContext, MisPedidosActivity::class.java)
                intent.putExtra("nombre", pastel.nombre)
                intent.putExtra("domicilio", pastel.domicilio)
                intent.putExtra("tamaño", pastel.tamaño)
                intent.putExtra("telefono", pastel.telefono)
                intent.putExtra("producto", pastel.producto)
                startActivity(intent)
            }
            R.id.itmProductos -> {
                intent = Intent(applicationContext, ProductosActivity::class.java)
                startActivity(intent)
            }
            R.id.itmNosotros -> {
                intent = Intent(applicationContext, NosotrosActivity::class.java)
                startActivity(intent)
            }
            R.id.itmCerrar -> {
                cerrarSesion()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cerrarSesion() {
        val preferences: SharedPreferences = getSharedPreferences("preferenciasusuario", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()

        editor.clear()
        editor.apply()

        val intent = Intent(applicationContext, AccesoActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP; Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}