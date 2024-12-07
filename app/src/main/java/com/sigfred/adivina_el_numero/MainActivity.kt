package com.sigfred.adivina_el_numero

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var numeroSecreto: Int = 0
    private var intentosRestantes: Int = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numeroSecreto = (1..100).random()
        intentosRestantes = 7

        val etGuess = findViewById<EditText>(R.id.et_guess)
        val btnGuess = findViewById<Button>(R.id.btn_guess)
        val tvResult = findViewById<TextView>(R.id.tv_result)
        val tvInfo = findViewById<TextView>(R.id.tv_info)

        btnGuess.setOnClickListener {
            val guess = etGuess.text.toString().toIntOrNull()

            if (guess != null) {
                intentosRestantes--

                if (guess == numeroSecreto) {
                    tvResult.text = "¡Felicidades! Adivinaste el número en ${7 - intentosRestantes} intentos."
                    btnGuess.isEnabled = false
                    etGuess.isEnabled = false
                } else {
                    val mensaje = if (guess < numeroSecreto) {
                        "El número es mayor. Te quedan $intentosRestantes intentos."
                    } else {
                        "El número es menor. Te quedan $intentosRestantes intentos."
                    }
                    tvResult.text = mensaje

                    if (intentosRestantes == 0) {
                        tvResult.text = "Perdiste. El número era $numeroSecreto."
                        btnGuess.isEnabled = false
                        etGuess.isEnabled = false
                    }
                }
            } else {
                Toast.makeText(this, "Ingresa un número válido", Toast.LENGTH_SHORT).show()
            }

            etGuess.text.clear()
        }
    }
}