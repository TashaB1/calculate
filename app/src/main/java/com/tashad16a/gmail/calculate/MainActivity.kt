package com.tashad16a.gmail.calculate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var textDisplay1: TextView // текстовое поле для вывода результата
    lateinit var textDisplay2: TextView// текстовое поле для вывода знака операции
    var result: Double = 0.0
    lateinit var postnot: String
    var enterFlag: Boolean = false
    val operation: String = "%^*/+-"
    var lastEpression: String? = ""
    var lastAction: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)    //Загрузка ресурса разметки осуществляется в методе Activity.onCreate

        // устанавливаем в качестве интерфейса файл activity_main.xml
        setContentView(R.layout.activity_main)
        // получаем поля по id из файла activity_main.xml
        textDisplay1 = findViewById(R.id.textViewD1)
        textDisplay2 = findViewById(R.id.textViewD2)
    }

    // сохранение состояния
    override fun onSaveInstanceState(outState: Bundle) {
        lastEpression = textDisplay1.getText().toString()
        outState.putString("EXPRESSION", lastEpression)
        if (!textDisplay2.getText().toString().equals("0")) {
            lastAction = textDisplay2.getText().toString()
            outState.putString("ACTION", lastAction)
        }
        super.onSaveInstanceState(outState)
    }

    // получение ранее сохраненного состояния
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lastEpression = savedInstanceState.getString("EXPRESSION")
        lastAction = savedInstanceState.getString("ACTION")
        textDisplay1.setText(lastEpression)
        textDisplay2.setText(lastAction)
    }

    // обработка нажатия на числовую кнопку
    fun onDigitClick(view: View) {

        val button = view as Button

        if (enterFlag || textDisplay1.getText().equals("Деление на ноль невозможно")) {
            textDisplay1.setText("")
            textDisplay2.setText("")
            enterFlag = false
        }

        if (textDisplay2.getText().toString().equals("0")) {
            textDisplay2.setText(button.getText())
        } else {
            textDisplay2.append(button.getText())
        }
    }
    // обработка нажатия на кнопку операцию (+-*/)
    fun onOperationClick(view: View) {

        val button = view as Button

        if (enterFlag) {
            textDisplay1.setText("")
            textDisplay2.setText("")
            enterFlag = false
        }

        if (!textDisplay2.getText().toString().equals("") && lastSymb(textDisplay2).equals(".")) {
            textDisplay1.append(textDisplay2.getText().toString() + "0" + button.getText())
            textDisplay2.setText("")
        }

        if (textDisplay1.getText().toString().equals("")) {
            if ((button.getText().toString().equals("-") || button.getText().toString().equals("+")) && textDisplay1.getText().toString().equals("")) {
                if (textDisplay2.getText().toString().equals("")) {
                    textDisplay1.setText(textDisplay1.getText().toString() + "0" + button.getText() + textDisplay2.getText().toString())
                    return
                }
            }

            if ((button.getText().toString().equals("/") || button.getText().toString().equals("*")) && textDisplay1.getText().toString().equals("")) {
                if (textDisplay2.getText().toString().equals("")) {
                    textDisplay1.setText("")
                    return
                }
            }
            textDisplay1.setText(textDisplay1.getText().toString() + textDisplay2.getText().toString() + button.getText())
            textDisplay2.setText("")
        } else {
            if ((!textDisplay1.getText().toString().equals("")) && (textDisplay2.getText().toString().equals("")) && (operation.indexOf(lastSymb(textDisplay1)) > -1)) {
                textDisplay1.setText(textDisplay1.getText().toString().substring(0, textDisplay1.getText().toString().length - 1) + button.getText())
                textDisplay2.setText("")
            } else {
                if (!textDisplay2.getText().toString().equals("") && !textDisplay1.getText().toString().equals("") && lastSymb(textDisplay1).equals("/")) {
                    System.out.print(lastSymb(textDisplay1))
                    if (textDisplay2.getText().toString().equals("0")) {
                        textDisplay2.setText("")
                        textDisplay1.setText("Деление на ноль невозможно")
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
    // обработка нажатия на кнопку "=" (Итог)
    fun onEnterClick(view: View) {

        val geo = GetExpressionOpz()
        val calc = CalcExpression()   /* "(50-5.3)-80"); */
        if (!textDisplay1.getText().toString().equals("")) {
            if (!textDisplay2.getText().toString().equals(""))
                textDisplay1.setText(textDisplay1.getText().toString() + textDisplay2.getText().toString())
            else textDisplay1.setText(textDisplay1.getText().toString() + "0")
            postnot = geo.ParseExpression(textDisplay1.getText().toString()) //Преобразовываем выражение в постфиксную запись
            result = calc.CalcExpression(postnot) //Решаем полученное выражение

            textDisplay2.setText(result.toString())
            enterFlag = true
        }
    }
    // обработка нажатия на иные кнопки
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
                    textDisplay2.setText("0" + button.getText())
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
        val lastSymb: String
        val indLastSymb: Int
        if (!expression.getText().equals("")) {
            indLastSymb = expression.getText().length - 1
            lastSymb = expression.getText().get(indLastSymb).toString()
            return lastSymb
        }
        return ""
    }
}
