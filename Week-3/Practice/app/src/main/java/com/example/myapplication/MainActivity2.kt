package com.example.practice

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.R

class MainActivity2 : AppCompatActivity() {
    private var send_message_button: Button? = null
    private var edit_text: EditText? = null
    private var MY_REQUEST_CODE = 10
    private var text_feedback: TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        this.edit_text = findViewById<EditText>(R.id.edit_text)
        this.send_message_button = findViewById<Button>(R.id.send_button);
        this.text_feedback = findViewById<TextView>(R.id.text_feedback)
        send_message_button?.setOnClickListener {
            sendMessage()
        }

        val backButton = findViewById<Button>(R.id.go_back)
        backButton.setOnClickListener {
            this.onBackPressed();
        }
    }
    private fun sendMessage() {
        val fullName = edit_text?.text.toString();
        val message = "Hello, Please say hello me!";

        val intent = Intent(this, MainActivity3::class.java)
        intent.putExtra("fullName", fullName)
        intent.putExtra("message", message)
        this.startActivityForResult(intent, MY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode : Int, resultCode : Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            val feedback = data?.getStringExtra("feedback")
            this.text_feedback?.text = feedback
        } else {
            this.text_feedback?.text = "!?"
        }
    }

}