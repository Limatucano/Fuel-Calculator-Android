package com.example.calcularcombustivel

import android.content.Context
import android.icu.text.NumberFormat
import android.os.Build
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged


class MainActivity : AppCompatActivity()
{
    private var litrosGasolina: Double = 0.0
    private var gasto: Double = 0.0
    private lateinit var result : TextView
    private lateinit var resulttxt : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        result= findViewById(R.id.resultado)
        resulttxt = findViewById(R.id.resultadotxt)

        val button: Button = findViewById(R.id.btn)
        var kmtotal: EditText = findViewById(R.id.kmTotal)
        var consumoLitro: EditText = findViewById(R.id.consumoLitro)
        var precoCombustivel: EditText = findViewById(R.id.precoCombustivel)


        button.setOnClickListener {
            it.hideKeyboard()
            if(kmtotal.text.length!=0 && consumoLitro.text.length!=0 && precoCombustivel.text.length!=0) {
                calcular(kmtotal.text.toString().toDouble(), consumoLitro.text.toString().toDouble(), precoCombustivel.text.toString().toDouble())
            }else {
                validate(kmtotal)
                validate(consumoLitro)
                validate(precoCombustivel)
            }

        }

    }

    fun validate(campo: EditText){
        if(campo.text.length==0) {
            return campo.setError("Campo Vazio")
        }
    }
    fun calcular(kmtotal: Double, consumoLitro: Double, precoCombustivel: Double) {

        litrosGasolina = kmtotal / consumoLitro
        gasto = litrosGasolina * precoCombustivel
        resulttxt.setText("Custo Aproximado:")
        result.setText("R$ %.2f".format(gasto.toString().toDouble()))
    }
    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}