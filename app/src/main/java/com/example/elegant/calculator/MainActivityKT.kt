package com.example.elegant.calculator

import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

class MainActivityKT() : AppCompatActivity() {

    lateinit var input: TextView
    lateinit var output: TextView
    private var val1 = Double.NaN
    private var val2 = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input = findViewById(R.id.input)
        output = findViewById(R.id.output)
        viewSetup()
    }

    private fun viewSetup() {
        initNumberButton(R.id.button0, getString(R.string.ZERO))
        initNumberButton(R.id.button1, getString(R.string.ONE))
        initNumberButton(R.id.button2, getString(R.string.TWO))
        initNumberButton(R.id.button3, getString(R.string.THREE))
        initNumberButton(R.id.button4, getString(R.string.FOUR))
        initNumberButton(R.id.button5, getString(R.string.FIVE))
        initNumberButton(R.id.button6, getString(R.string.SIX))
        initNumberButton(R.id.button7, getString(R.string.SEVEN))
        initNumberButton(R.id.button8, getString(R.string.EIGHT))
        initNumberButton(R.id.button9, getString(R.string.NINE))
        initDotButton()
        initModulusButton()
        initAddButton()
        initSubstractionButton()
        initMultiplicationButton()
        initDivideButton()
        initExtraButton()
        initEqualButton()
        initClearButton()

    }

    private fun initDotButton() {
        val dotButton = findViewById<Button>(R.id.button_dot)
        dotButton.setOnClickListener {
            exceedLength()
            val textValue = input.text.toString() + getString(R.string.DOT)
            input.text = textValue
        }
    }

    private fun initModulusButton() {
        val modulButton = findViewById<Button>(R.id.button_modul)

        modulButton.setOnClickListener {
            if (input.text.length > 0) {
                operation(fun(x: Double, y: Double): Double = x % y)
                if (!ifReallyDecimal()) {
                    val outputString = val1.toString() + getString(R.string.PERCENT)
                    output.text = outputString
                } else {
                    val outputString = val1.toInt().toString() + getString(R.string.PERCENT)
                    output.text = outputString
                }
                input.setText(null)
            } else {
                output.text = getString(R.string.ERROR)
            }
        }
    }

    private fun initAddButton() {
        val addButton = findViewById<Button>(R.id.button_add)
        addButton.setOnClickListener {
            if (input.text.isNotEmpty()) {
                operation(fun(x: Double, y: Double): Double = x + y)
                if (!ifReallyDecimal()) {
                    val outputText = val1.toString() + R.string.PLUS
                    output.text = outputText
                } else {
                    val outputText = val1.toInt().toString() + getString(R.string.PLUS)
                    output.text = outputText
                }
                input.text = null
            } else {
                output.text = getString(R.string.ERROR)
            }
        }
    }

    private fun initExtraButton() {
        val equalButton = findViewById<Button>(R.id.button_equal)
        equalButton.setOnClickListener {
            if (!output.text.toString().isEmpty() || !input.text.toString().isEmpty()) {
                val1 = input.text.toString().toDouble()
                val outputText = R.string.MINUS.toChar() + input.text.toString()
                output.text = outputText
                input.text = ""
            } else {
                output.text = getString(R.string.ERROR)
            }
        }
    }

    private fun initMultiplicationButton() {
        val multiButton = findViewById<Button>(R.id.button_multi)
        multiButton.setOnClickListener {
            if (input.text.isNotEmpty()) {
                operation(fun(x: Double, y: Double): Double = x * y)
                if (!ifReallyDecimal()) {
                    val outputText = val1.toString() + R.string.MULTIPLY
                    output.text = outputText
                } else {
                    val outputText = val1.toInt().toString() + getString(R.string.MULTIPLY)
                    output.text = outputText
                }
                input.text = null
            } else {
                output.text = getString(R.string.ERROR)
            }
        }
    }

    private fun initSubstractionButton() {
        val subButton = findViewById<Button>(R.id.button_sub)
        subButton.setOnClickListener {
            if (input.text.isNotEmpty()) {
                operation(fun(x: Double, y: Double): Double = x - y)
                if (input.text.length > 0) if (!ifReallyDecimal()) {
                    val outputString = val1.toString() + getString(R.string.MINUS)
                    output.text = outputString
                } else {
                    val outputString = val1.toInt().toString() + getString(R.string.MINUS)
                    output.text = outputString
                }
                input.text = null
            } else {
                output.text = getString(R.string.ERROR)
            }
        }
    }

    private fun initDivideButton() {
        val divideButton = findViewById<Button>(R.id.button_divide)
        divideButton.setOnClickListener {
            if (input.text.isNotEmpty()) {
                operation(fun(x: Double, y: Double): Double = x / y)
                if (ifReallyDecimal()) {
                    val outputText = val1.toInt().toString() + getString(R.string.DIVISION)
                    output.text = outputText
                } else {
                    val outputText = val1.toString() + getString(R.string.DIVISION)
                    output.text = outputText
                }
                input.text = null
            } else {
                output.text = getString(R.string.ERROR)
            }
        }
    }

    private fun initEqualButton() {
        val equalButton = findViewById<Button>(R.id.button_equal)
        equalButton!!.setOnClickListener {
            if (input.text.isNotEmpty()) {
                operation(fun(x:Double,y:Double) :Double = x)
                if (!ifReallyDecimal()) {
                    output.text = val1.toString()
                } else {
                    output.text = val1.toInt().toString()
                }
                input.text = null
            } else {
                output.text = getString(R.string.ERROR)
            }
        }
    }

    private fun initClearButton() {
        val clearButton = findViewById<Button>(R.id.button_clear)
        clearButton!!.setOnClickListener {
            if (input.text.isNotEmpty()) {
                val name: CharSequence = input!!.text.toString()
                input.text = name.subSequence(0, name.length - 1)
            } else {
                val1 = Double.NaN
                val2 = Double.NaN
                input.text = ""
                output.text = ""
            }
        }

        // Empty text views on long click.
        clearButton.setOnLongClickListener {
            val1 = Double.NaN
            val2 = Double.NaN
            input.text = ""
            output.text = ""
            true
        }
    }

    // Remove error message that is already written there.
    private fun ifErrorOnOutput() {
        if (output.text.toString() == "Error") {
            output.text = ""
        }
    }

    // Make text small if too many digits.
    private fun exceedLength() {
        if (input.text.toString().length > 10) {
            input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        }
    }

    private fun initNumberButton(@IdRes id: Int, value: String) {
        val button = findViewById<Button>(id)
        button.setOnClickListener {
            ifErrorOnOutput()
            exceedLength()
            val textValue = input.text.toString() + value
            input.text = textValue
        }
    }

    private fun operation(action: (Double, Double) -> Double) {
        if (!java.lang.Double.isNaN(val1)) {
            if (output.text.toString()[0] == R.string.MINUS.toChar()) {
                val1 *= -1
            }
            val2 = input.text.toString().toDouble()
            val1 = action.invoke(val1, val2)
        } else {
            val1 = input.text.toString().toDouble()
        }
    }


    // Whether value if a double or not
    private fun ifReallyDecimal(): Boolean {
        return val1 == val1.toInt().toDouble()
    }


}