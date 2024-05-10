package com.example.saanapp

import android.content.Intent
import android.media.MediaCodec.QueueRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {
    var txNombre: EditText? = null
    var txCorreo: EditText? = null
    var txContraseña: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txNombre = findViewById(R.id.txNombre)
        txCorreo = findViewById(R.id.txCorreo)
        txContraseña = findViewById(R.id.txContraseña)

        val tvGoLogin = findViewById<TextView>(R.id.tv_go_to_login)
        tvGoLogin.setOnClickListener {
            goToLogin()
        }

    }

    fun ClickBtnInsertar(view: View) {
        val url = "http://localhost/conexion_php_android/insertar.php"
        val queue = Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(this, "Usuario insertado exitosamente", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val parametros=hashMapOf<String, String>()
                    parametros.put("Nombre",txNombre?.text.toString())
                    parametros.put("Correo",txCorreo?.text.toString())
                    parametros.put("Contraseña",txContraseña?.text.toString())
                return parametros
                }
            }
        queue.add(resultadoPost)

    }
    private fun goToLogin() {
        val i = Intent(this, inicio_sesion::class.java)
        startActivity(i)
    }
}




