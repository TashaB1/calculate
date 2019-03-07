package com.tashad16a.gmail.calculate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Button

@ConstantName
class MainActivity : AppCompatActivity() {
    lateinit var textDisplay1: TextView
    lateinit var textDisplay2: TextView
    lateinit var postfixNotation: String
    var result: Double = 0.0
    var enterFlag: Boolean = false
    var lastEpression: String? = ""
    var lastAction: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        textDisplay1 = findViewById(R.id.textViewD1)
        textDisplay2 = findViewById(R.id.textViewD2)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        lastEpression = textDisplay1.getText().toString()
        outState.putString("EXPRESSION", lastEpression)

        if (!textDisplay2.getText().toString().equals(ConstantName.ZERO_DIGIT)) {
            lastAction = textDisplay2.getText().toString()
            outState.putString("ACTION", lastAction)
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        lastEpression = savedInstanceState.getString("EXPRESSION")
        lastAction = savedInstanceState.getString("ACTION")
        textDisplay1.setText(lastEpression)
        textDisplay2.setText(lastAction)
    }

    fun onDigitClick(view: View) {
        val button = view as Button

        if (enterFlag || textDisplay1.getText().equals(ConstantName.ERROR_DIVISION_BY_ZERO)) {
            textDisplay1.setText("")
            textDisplay2.setText("")
            enterFlag = false
        }

        if (textDisplay2.getText().toString().equals(ConstantName.ZERO_DIGIT)) {
            textDisplay2.setText(button.getText())
        } else {
            textDisplay2.append(button.getText())
        }
    }

    fun onOperationClick(view: View) {
        val button = view as Button

        if (enterFlag) {
            textDisplay1.setText("")
            enterFlag = false
        }

        if (!textDisplay2.getText().toString().equals("") && lastSymb(textDisplay2).equals(ConstantName.POINT)) {
            textDisplay1.append(textDisplay2.getText().toString() + ConstantName.ZERO_DIGIT + button.getText())
            textDisplay2.setText("")
        }

        if (textDisplay1.getText().toString().equals("")) {

            if ((button.getText().toString().equals(ConstantName.SUBSTRACTION_OPERATION) || button.getText().toString().equals(ConstantName.ADDITION_OPERATION)) && textDisplay1.getText().toString().equals("")) {
                if (textDisplay2.getText().toString().equals("")) {
                    textDisplay1.setText(textDisplay1.getText().toString() + ConstantName.ZERO_DIGIT + button.getText() + textDisplay2.getText().toString())

                    return
                }
            }

            if ((button.getText().toString().equals(ConstantName.DIVISION_OPERATION) || button.getText().toString().equals(ConstantName.MULTIPLICATION_OPERATION)) && textDisplay1.getText().toString().equals("")) {
                if (textDisplay2.getText().toString().equals("")) {
                    textDisplay1.setText("")

                    return
                }
            }

            textDisplay1.setText(textDisplay1.getText().toString() + textDisplay2.getText().toString() + button.getText())
            textDisplay2.setText("")
        } else {
            if ((!textDisplay1.getText().toString().equals("")) && (textDisplay2.getText().toString().equals("")) && (ConstantName.OPERATIONS.indexOf(lastSymb(textDisplay1)) > -1)) {
                textDisplay1.setText(textDisplay1.getText().toString().substring(0, textDisplay1.getText().toString().length - 1) + button.getText())
                textDisplay2.setText("")
            } else {
                if (!textDisplay2.getText().toString().equals("") && !textDisplay1.getText().toString().equals("") && lastSymb(textDisplay1).equals(ConstantName.DIVISION_OPERATION)) {
                    if (textDisplay2.getText().toString().equals(ConstantName.ZERO_DIGIT)) {
                        textDisplay2.setText("")
                        textDisplay1.setText(ConstantName.ERROR_DIVISION_BY_ZERO)
                        enterFlag = true

                        return
                    }
                } else {
                    textDisplay1.setText(textDisplay1.getText().toString() + textDisplay2.getText().toString() + button.getText())
                    textDisplay2.setText("")
                }
            }
        }
    }

    fun onEnterClick(view: View) {
        val geo = GetExpressionOpz()
        val calc = CalcExpression()
        var expression: String? = ""

        if (!textDisplay1.getText().toString().equals("")) {
            if (!textDisplay2.getText().toString().equals("")) {
                if (!textDisplay2.getText().toString().equals(ConstantName.ZERO_DIGIT)) {
                    textDisplay1.setText(textDisplay1.getText().toString() + textDisplay2.getText().toString())
                    expression = textDisplay1.getText().toString()
                    if (textDisplay1.getText().get(0).toString().equals(ConstantName.SUBSTRACTION_OPERATION)) {
                        expression = ConstantName.ZERO_DIGIT + textDisplay1.getText().toString()
                    }
                } else {
                    if (!textDisplay1.getText().toString().equals("") && lastSymb(textDisplay1).equals(ConstantName.DIVISION_OPERATION)) {
                        textDisplay2.setText("")
                        textDisplay1.setText(ConstantName.ERROR_DIVISION_BY_ZERO)
                        enterFlag = true

                        return
                    } else {
                        expression = textDisplay1.getText().toString() + textDisplay2.getText().toString()
                    }
                }

                postfixNotation = geo.ParseExpression(expression)
                result = calc.CalcExpression(postfixNotation)
                textDisplay2.setText(result.toString())
            } else {
                textDisplay1.setText("")
            }
            enterFlag = true
        } else {
            textDisplay2.setText("")
        }
    }

    fun onOtherActionClick(view: View) {

        val button = view as Button

        when (button.getText()) {
            "CE" -> {
                textDisplay1.setText("")
                textDisplay2.setText("")
            }
            "C" -> textDisplay2.setText("")
            "." -> {
                if (textDisplay2.getText().toString().equals("")) {
                    textDisplay2.setText(ConstantName.ZERO_DIGIT + button.getText())
                } else {
                    if (textDisplay2.getText().toString().indexOf(button.getText().toString()) == -1) {
                        textDisplay2.append(button.getText())
                    }
                }
            }
            "←" -> {
                if (textDisplay2.getText().toString().length > 0) {
                    textDisplay2.setText(textDisplay2.getText().toString().substring(0, textDisplay2.getText().toString().length - 1))
                }
            }
        }
    }

    fun lastSymb(expression: TextView): String {
        val lastSymbol: String
        val indLastSymbol: Int

        if (!expression.getText().equals("")) {
            indLastSymbol = expression.getText().length - 1
            lastSymbol = expression.getText().get(indLastSymbol).toString()

            return lastSymbol
        }

        return ""
    }
}
