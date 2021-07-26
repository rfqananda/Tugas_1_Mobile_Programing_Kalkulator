package com.rifqiananda.aplikasikalkulator

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fathzer.soft.javaluator.DoubleEvaluator
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_1.setOnClickListener { appendOnExpression(true, "1") }
        btn_2.setOnClickListener { appendOnExpression(true, "2") }
        btn_3.setOnClickListener { appendOnExpression(true, "3") }
        btn_4.setOnClickListener { appendOnExpression(true, "4") }
        btn_5.setOnClickListener { appendOnExpression(true, "5") }
        btn_6.setOnClickListener { appendOnExpression(true, "6") }
        btn_7.setOnClickListener { appendOnExpression(true, "7") }
        btn_8.setOnClickListener { appendOnExpression(true, "8") }
        btn_9.setOnClickListener { appendOnExpression(true, "9") }
        btn_0.setOnClickListener { appendOnExpression(true, "0") }
        btn_dot.setOnClickListener { appendOnExpression(true, ".") }

        btn_plus.setOnClickListener { appendOnExpression(false, "+") }
        btn_minus.setOnClickListener { appendOnExpression(false, "-") }
        btn_multiplication.setOnClickListener { appendOnExpression(false, "*") }
        btn_divide.setOnClickListener { appendOnExpression(false, "/") }

        btn_percent.setOnClickListener {
            val number : Double = tvInput.text.toString().toDouble()/100
            tvOutput.setText(number.toString())
        }

        btn_delete.setOnClickListener {
            val string = tvInput.text.toString()
            if(string.isNotEmpty())
            {
                tvInput.text = string.substring(0, string.length-1)
            }
            tvOutput.text = ""
        }

        btn_clear.setOnClickListener {
            clear()
        }

        btn_equal.setOnClickListener {
            calculate()
        }
    }

    fun appendOnExpression (clear: Boolean, string: String)
    {
        if (clear)
        {
            tvOutput.text = ""
            tvInput.append(string)
        }

        else
        {
            tvInput.append(tvOutput.text)
            tvInput.append(string)
            tvOutput.text = ""
        }
    }

    private fun clear()
    {
        tvInput.text = ""
        tvOutput.text = ""

    }

    private fun calculate()
    {

        try {
            val input = ExpressionBuilder(tvInput.text.toString()).build()
            val output = input.evaluate()
            val longOutput = output.toLong()
            if (output == longOutput.toDouble()){
                tvOutput.text = longOutput.toString()
            }else{
                tvOutput.text = output.toString()
            }

        }catch (e:Exception){
            Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
        }
    }
}