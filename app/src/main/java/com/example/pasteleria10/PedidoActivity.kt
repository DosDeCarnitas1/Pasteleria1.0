package com.example.pasteleria10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PedidoActivity : AppCompatActivity() {

    private lateinit var nombre: EditText
    private lateinit var domicilio: EditText
    private lateinit var producto: Spinner
    private lateinit var prodSel: String
    private lateinit var chico: Switch
    private lateinit var mediano: Switch
    private lateinit var familiar: Switch
    private lateinit var telefono: EditText
    private var pastel: Pastel = Pastel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        nombre = findViewById(R.id.edtNombre)
        domicilio = findViewById(R.id.edtDomicilio)
        producto = findViewById(R.id.spnProducto)
        chico = findViewById(R.id.swtChico)
        mediano = findViewById(R.id.swtMediano)
        familiar = findViewById(R.id.swtFamiliar)
        telefono = findViewById(R.id.edtTelefono)

        val opciones = resources.getStringArray(R.array.productos)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
        producto.adapter = adaptador
        prodSel = opciones[0]
        producto.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                prodSel = opciones[2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        chico.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mediano.isChecked = false
                familiar.isChecked = false
                pastel.tamaño = "Chico"
            }
        }

        mediano.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                chico.isChecked = false
                familiar.isChecked = false
                pastel.tamaño = "Mediano"
            }
        }

        familiar.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                chico.isChecked = false
                mediano.isChecked = false
                pastel.tamaño = "Familiar"
            }
        }
    }

    fun onClick (view: View?) {
        when (view?.id) {
            R.id.btnPedido -> pedido()
            R.id.btnCancelar -> cancelar()
        }
    }

    private fun pedido() {
        if (nombre.text.isNotEmpty() && nombre.text.isNotBlank() && domicilio.text.isNotEmpty() && domicilio.text.isNotBlank()
            && telefono.text.isNotEmpty() && telefono.text.isNotBlank()){
            if (!chico.isChecked && !mediano.isChecked && !familiar.isChecked){
                Toast.makeText(this, "Selecciona un tamaño para el pastel", Toast.LENGTH_LONG).show()
                return
            }
            pastel.nombre = nombre.text.toString()
            pastel.domicilio = domicilio.text.toString()
            pastel.telefono = telefono.text.toString()
            pastel.producto = prodSel

            Toast.makeText(this, "Pedido realizado.", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("nombre", pastel.nombre)
            intent.putExtra("domicilio", pastel.domicilio)
            intent.putExtra("tamaño", pastel.tamaño)
            intent.putExtra("telefono", pastel.telefono)
            intent.putExtra("producto", pastel.producto)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Realiza un pedido!", Toast.LENGTH_LONG).show()
        }
    }

    private fun cancelar() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}