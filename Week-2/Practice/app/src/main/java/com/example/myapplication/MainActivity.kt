package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity


class MainActivity : ComponentActivity() {

    private val items = arrayOf(
        "Android", "IPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"
    )

    private lateinit var selection: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spinner)

        selection = findViewById(R.id.selection)
        val spin = findViewById<Spinner>(R.id.spinner)



        val aa = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            items
        )

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = aa
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                onSpinnerItemSelected(parent, view, position, id)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                if (parent != null) {
                    onSpinnerNothingSelected(parent)
                }
            }
        }

        registerForContextMenu(selection)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_1 -> {
                Toast.makeText(this, "Slide Selected", Toast.LENGTH_SHORT).show()
            }
            R.id.item_2_1 -> {
                Toast.makeText(this, "BT 1 selected", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity2::class.java))
            }
            R.id.item_2_2 -> {
                Toast.makeText(this, "BT 2 selected", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity3::class.java))
            }
        }
        return true
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.option_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_1 -> {
                Toast.makeText(this, "Slide Selected", Toast.LENGTH_SHORT).show()
            }
            R.id.item_2_1 -> {
                Toast.makeText(this, "BT 1 selected", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity2::class.java))
            }
            R.id.item_2_2 -> {
                Toast.makeText(this, "BT 2 selected", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity3::class.java))
            }
        }
        return super.onContextItemSelected(item)
    }

    fun onSpinnerItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
        selection.text = items[position]
    }

    fun onSpinnerNothingSelected(parent: AdapterView<*>) {
        selection.text = ""
    }
}
