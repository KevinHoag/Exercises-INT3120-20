package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity3 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_relative_layout)
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

        val title = findViewById<TextView>(R.id.titleTextView)
        registerForContextMenu(title)
    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.option_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_1 -> {
                Toast.makeText(this, "Slide Selected", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.item_2_1 -> {
                Toast.makeText(this, "BT 1 selected", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity2::class.java))
            }
            R.id.item_2_2 -> {
                Toast.makeText(this, "BT 2 selected", Toast.LENGTH_SHORT).show()

            }
        }
        return super.onContextItemSelected(item)
    }
}