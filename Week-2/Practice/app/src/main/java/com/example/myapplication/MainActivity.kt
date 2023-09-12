package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_constraint_layout)

//         Dành cho order layout
        val name = findViewById<EditText>(R.id.enter_your_name)
        val phoneNumber = findViewById<EditText>(R.id.enter_your_phone_number)
        val cheeseButton = findViewById<RadioButton>(R.id.cheese)
        val cheeseX2Button = findViewById<RadioButton>(R.id.cheese_x_2)
        val noneButton = findViewById<RadioButton>(R.id.none)
        val squareButton = findViewById<RadioButton>(R.id.square)
        val roundButton = findViewById<RadioButton>(R.id.round)
        val pepperoniButton = findViewById<CheckBox>(R.id.pepperoni)
        val mushroomButton = findViewById<CheckBox>(R.id.mushroom)
        val veggiesButton = findViewById<CheckBox>(R.id.veggies)
        val orderText = findViewById<TextView>(R.id.order_text)
        val orderButton = findViewById<Button>(R.id.place_order)

        orderButton.setOnClickListener{
                var tempOrderText: String = ""

            if (name.text.isNotEmpty()) {
                tempOrderText = tempOrderText + name.text + '\n'
            }
                if (phoneNumber.text.isNotEmpty()) {
                    tempOrderText = tempOrderText + phoneNumber.text + '\n'
                }
                if (cheeseButton.isChecked) {
                    tempOrderText = tempOrderText + "Cheese" + '\n'
                }
                if (cheeseX2Button.isChecked) {
                    tempOrderText = tempOrderText + "2 x Cheese" + '\n'
                }
                if (noneButton.isChecked) {
                    tempOrderText = tempOrderText + "None" + '\n'
                }
                if (squareButton.isChecked) {
                    tempOrderText = tempOrderText + "Square" + '\n'
                }
                if (roundButton.isChecked) {
                    tempOrderText = tempOrderText + "Round" + '\n'
                }
                if (pepperoniButton.isChecked) {
                    tempOrderText = tempOrderText + "Pepperoni" + '\n'
                }
                if (mushroomButton.isChecked) {
                    tempOrderText = tempOrderText + "Mushroom" + '\n'
                }
                if (veggiesButton.isChecked) {
                    tempOrderText = tempOrderText + "Veggies" + '\n'
                }
                orderText.text = tempOrderText

        }
        }


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
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}