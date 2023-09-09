package com.example.week2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.week2.ui.theme.Week2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donation_layout);

        val numberPicker = findViewById<NumberPicker>(R.id.picker1);
        numberPicker.minValue = 1;
        numberPicker.maxValue = 1000;

        val donateButton = findViewById<Button>(R.id.donate_button);
        val editText = findViewById<EditText>(R.id.edit_text);
        val totalMoney = findViewById<TextView>(R.id.total_money);
        donateButton.setOnClickListener{
            if (editText.text.isNotEmpty()) {
                val bonusMoney = editText.text.toString().toInt();
                val currentMoney = totalMoney.text.toString().toInt();
                totalMoney.text = (bonusMoney + currentMoney).toString();
            }

        }

    }
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
    Week2Theme {
        Greeting("Android")
    }
}