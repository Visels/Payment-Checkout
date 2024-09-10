package com.lemu.pay.checkout.home

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.lemu.pay.checkout.home.logic.ExEvaluation
import com.lemu.pay.checkout.payment.PaymentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CalculatorViewModel(application: Application) : AndroidViewModel(application) {

    init {
        Log.d("CALCULATOR VIEW MODEL", "view model initialized")
    }

    val calculator = ExEvaluation()

    var input by mutableStateOf("")
        private set

    var result by mutableStateOf("")
        private set


    fun updateInput(key: String) {
        if (key == "=") {
            input = result
            result = ""
            return
        }
        else input = when (key) {
            "C" -> ""
            "back" -> if (input.isNotBlank()) input.dropLast(1) else ""
            "." -> if (input.isEmpty()) "0." else if (input.contains(".")) input else input + key
            "+/-" -> if (input.startsWith("-")) input.removePrefix("-") else "-$input"
            in symbols() -> if (input.isNotEmpty() && input.last().isDigit()) input + key else input
            else -> input + key
        }
        if (input.isNotBlank() && input.last().toString() !in symbols()) calculate()
        else result = ""
    }

    private fun calculate() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    result = calculator.evaluate(input).toString()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(getApplication(), "Number limit exceeded", Toast.LENGTH_SHORT).show()
                input = input.dropLast(1)
            }
        }
    }
}

fun symbols() = listOf("+", "-", "*", "/", "%")