package com.example.pasteleria10

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AccesoActivity: AppCompatActivity() {

    private lateinit var correo: EditText
    private lateinit var contraseña: EditText
    private lateinit var guardar: Switch
    private lateinit var pastel: Pastel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)

        correo = findViewById(R.id.edtCorreo)
        contraseña = findViewById(R.id.edtContraseña)
        guardar = findViewById(R.id.swtGuardar)

        pastel = Pastel()
    }

    fun onClick(view: View?) {
        when(view?.id){
            R.id.btnRegistrar -> ingresar()
            R.id.btnSalir -> salir()
        }
    }

    private fun ingresar(){
        if (correo.text.isNotBlank() && correo.text.isNotEmpty() &&
            contraseña.text.isNotBlank() && contraseña.text.isNotEmpty()) {
            val usr =
                Usuario(correo.text.toString(), contraseña.text.toString(), true)
            if (guardar.isChecked) {
                guardarPreferencias(usr)
            }
            val intent = Intent(applicationContext, MenuActivity::class.java)
            intent.putExtra("nombre", pastel.nombre)
            intent.putExtra("domicilio", pastel.domicilio)
            intent.putExtra("producto", pastel.producto)
            intent.putExtra("tamaño", pastel.tamaño)
            intent.putExtra("telefono", pastel.telefono)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Camptura la información", Toast.LENGTH_LONG).show()
        }
    }

    private fun salir(){
        salir()
    }

    private fun guardarPreferencias(user: Usuario) {
        val preferences: SharedPreferences = getSharedPreferences("preferenciaUsuario", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString("email", user.correo)
        editor.putString("password", user.contraseña)
        editor.putBoolean("guardado", user.guardado)
        editor.apply()
    }
}