package com.example.pasteleria10

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private lateinit var intent: Intent
    private lateinit var pastel: Pastel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pastel = Pastel()

        Timer().schedule(object : TimerTask() {
            override fun run() {
                intent = if (nuevoUsuario()) {
                    Intent(applicationContext, MenuActivity::class.java).apply {
                        putExtra("nombre", pastel.nombre)
                        putExtra("domicilio", pastel.domicilio)
                        putExtra("tamaño", pastel.tamaño)
                        putExtra("telefono", pastel.telefono)
                        putExtra("producto", pastel.producto)
                    }
                } else {
                    Intent(applicationContext, AccesoActivity::class.java)
                }
                startActivity(intent)
            }

        }, 2000)
    }

    fun nuevoUsuario(): Boolean{
        val preferences: SharedPreferences = getSharedPreferences("preferenciasUsuario", MODE_PRIVATE)
        return preferences.getBoolean("guardado", false)
    }
}