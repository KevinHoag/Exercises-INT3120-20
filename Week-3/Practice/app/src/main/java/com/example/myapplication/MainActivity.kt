package com.example.practice

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R


class MainActivity : AppCompatActivity() {

    private var popup_button: Button? = null
    private var menu_anchor_button: Button? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        val button = findViewById<TextView>(R.id.hello_button)
        registerForContextMenu(button)

        this.popup_button = findViewById(R.id.popup_button)
        this.menu_anchor_button = findViewById(R.id.menu_anchor)

        this.popup_button?.setOnClickListener { v ->
            popUpButtonClicked()
        }

        val screen2 = findViewById<Button>(R.id.screen_2)
        screen2.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        val screen4 = findViewById<Button>(R.id.screen_4)
        screen4.setOnClickListener {
            startActivity(Intent(this, MainActivity4::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_1 -> Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show()
            R.id.item_2 -> Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show()
            R.id.item_3_1 -> Toast.makeText(this, "Item 3.1 selected", Toast.LENGTH_SHORT).show()
            R.id.item_3_2 -> Toast.makeText(this, "Item 3.2 selected", Toast.LENGTH_SHORT).show()
            R.id.item_4_1 -> Toast.makeText(this, "Item 4.1 selected", Toast.LENGTH_SHORT).show()
            R.id.item_4_2 -> Toast.makeText(this, "Item 4.2 selected", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_1 -> Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show()
            R.id.item_2 -> Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show()
            R.id.item_3_1 -> Toast.makeText(this, "Item 3.1 selected", Toast.LENGTH_SHORT).show()
            R.id.item_3_2 -> Toast.makeText(this, "Item 3.2 selected", Toast.LENGTH_SHORT).show()
            R.id.item_4_1 -> Toast.makeText(this, "Item 4.1 selected", Toast.LENGTH_SHORT).show()
            R.id.item_4_2 -> Toast.makeText(this, "Item 4.2 selected", Toast.LENGTH_SHORT).show()
        }
        return super.onContextItemSelected(item)
    }

    fun popUpButtonClicked() {
        val popup = PopupMenu(this, this.menu_anchor_button);
        popup.inflate(R.menu.popup_menu)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.item_1 -> {
                    Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_2 -> {
                    Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_3_1 -> {
                    Toast.makeText(this, "Item 3.1 selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_3_2 -> {
                    Toast.makeText(this, "Item 3.2 selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_4_1 -> {
                    Toast.makeText(this, "Item 4.1 selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_4_2 -> {
                    Toast.makeText(this, "Item 4.2 selected", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false // Return false for unknown menu items
            }
        }
        popup.show()
    }

}

//        setContent {
//            PracticeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MyApp()
//                }
//            }
//        }

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyApp() {
//    var normalMenu = NormalMenu()
//    var testMenu = TestMenu()
//    PracticeTheme {
//        normalMenu.show()
//    }
//}

