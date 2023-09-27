package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donation_linear_layout)

        val numberPicker = findViewById<NumberPicker>(R.id.picker1)
        numberPicker.minValue = 1
        numberPicker.maxValue = 1000

        val donateButton = findViewById<Button>(R.id.donate_button)
        val editText = findViewById<EditText>(R.id.edit_text)
        val totalMoney = findViewById<TextView>(R.id.total_money)
        donateButton.setOnClickListener{
            if (editText.text.isNotEmpty()) {
                val bonusMoney = editText.text.toString().toInt()
                val currentMoney = totalMoney.text.toString().toInt()
                totalMoney.text = (bonusMoney + currentMoney).toString()
            }

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

            }
            R.id.item_2_2 -> {
                Toast.makeText(this, "BT 2 selected", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity3::class.java))
            }
        }
        return super.onContextItemSelected(item)
    }
}
