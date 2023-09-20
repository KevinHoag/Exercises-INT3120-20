package com.example.date_time

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import java.text.DateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    var fmtDateAndTime = DateFormat.getDateTimeInstance()
    var lblDateAndTime: TextView? = null
    var myCalendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donation_table_layout)
    }
}
//        lblDateAndTime = findViewById(R.id.lblDateAndTime)
//        val btnDate: Button = findViewById(R.id.btnDate)
//        btnDate.setOnClickListener {
//            DatePickerDialog(
//                this@MainActivity, d, myCalendar[Calendar.YEAR],
//                myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH]
//            ).show()
//        }
//        val btnTime: Button = findViewById(R.id.btnTime)
//        btnTime.setOnClickListener {
//            TimePickerDialog(
//                this@MainActivity, t,
//                myCalendar[Calendar.HOUR_OF_DAY],
//                myCalendar[Calendar.MINUTE], true
//            ).show()
//        }
//        updateLabel()
//    }
//
//    private fun updateLabel() {
//        val dateTimeString = fmtDateAndTime.format(myCalendar.time)
//        lblDateAndTime!!.text = dateTimeString
//    }
//
//    var d =
//        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//            myCalendar[Calendar.YEAR] = year
//            myCalendar[Calendar.MONTH] = monthOfYear
//            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
//            updateLabel()
//        }
//    var t =
//        OnTimeSetListener { view, hourOfDay, minute ->
//            myCalendar[Calendar.HOUR_OF_DAY] = hourOfDay
//            myCalendar[Calendar.MINUTE] = minute
//            updateLabel()
//        }

//         Dành cho order layout
//        val name = findViewById<EditText>(R.id.enter_your_name)
//        val phoneNumber = findViewById<EditText>(R.id.enter_your_phone_number)
//        val cheeseButton = findViewById<RadioButton>(R.id.cheese)
//        val cheeseX2Button = findViewById<RadioButton>(R.id.cheese_x_2)
//        val noneButton = findViewById<RadioButton>(R.id.none)
//        val squareButton = findViewById<RadioButton>(R.id.square)
//        val roundButton = findViewById<RadioButton>(R.id.round)
//        val pepperoniButton = findViewById<CheckBox>(R.id.pepperoni)
//        val mushroomButton = findViewById<CheckBox>(R.id.mushroom)
//        val veggiesButton = findViewById<CheckBox>(R.id.veggies)
//        val orderText = findViewById<TextView>(R.id.order_text)
//        val orderButton = findViewById<Button>(R.id.place_order)
//
//        orderButton.setOnClickListener{
//                var tempOrderText: String = ""
//
//            if (name.text.isNotEmpty()) {
//                tempOrderText = tempOrderText + name.text + '\n'
//            }
//                if (phoneNumber.text.isNotEmpty()) {
//                    tempOrderText = tempOrderText + phoneNumber.text + '\n'
//                }
//                if (cheeseButton.isChecked) {
//                    tempOrderText = tempOrderText + "Cheese" + '\n'
//                }
//                if (cheeseX2Button.isChecked) {
//                    tempOrderText = tempOrderText + "2 x Cheese" + '\n'
//                }
//                if (noneButton.isChecked) {
//                    tempOrderText = tempOrderText + "None" + '\n'
//                }
//                if (squareButton.isChecked) {
//                    tempOrderText = tempOrderText + "Square" + '\n'
//                }
//                if (roundButton.isChecked) {
//                    tempOrderText = tempOrderText + "Round" + '\n'
//                }
//                if (pepperoniButton.isChecked) {
//                    tempOrderText = tempOrderText + "Pepperoni" + '\n'
//                }
//                if (mushroomButton.isChecked) {
//                    tempOrderText = tempOrderText + "Mushroom" + '\n'
//                }
//                if (veggiesButton.isChecked) {
//                    tempOrderText = tempOrderText + "Veggies" + '\n'
//                }
//                orderText.text = tempOrderText
//
//        }
//        }


// Dành cho donation layout
//        val numberPicker = findViewById<NumberPicker>(R.id.picker1)
//        numberPicker.minValue = 1
//        numberPicker.maxValue = 1000
//
//        val donateButton = findViewById<Button>(R.id.donate_button)
//        val editText = findViewById<EditText>(R.id.edit_text)
//        val totalMoney = findViewById<TextView>(R.id.total_money)
//        donateButton.setOnClickListener{
//            if (editText.text.isNotEmpty()) {
//                val bonusMoney = editText.text.toString().toInt()
//                val currentMoney = totalMoney.text.toString().toInt()
//                totalMoney.text = (bonusMoney + currentMoney).toString()
//            }
//
//        }


