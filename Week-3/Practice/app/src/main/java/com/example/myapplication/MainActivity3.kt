package com.example.practice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.R

class MainActivity3 : AppCompatActivity() {
    private var text_receive: TextView? = null
    private var resend_button: Button? = null
    private var fullName = ""
    private var message = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        this.resend_button = findViewById(R.id.resend_button)
        this.text_receive = findViewById(R.id.text_receive)
        this.resend_button?.setOnClickListener{
            goBack();
        }

        val intent = this.intent;
        this.fullName = intent.getStringExtra("fullName").toString()

        this.message = intent.getStringExtra("message").toString()
        this.text_receive?.text = this.message
        if (this.fullName == "") {
            this.text_receive?.text = "?????";
        } else if (intent.getStringExtra("fullName") == null) {
            this.text_receive?.text = "????";
        }
    }
    private fun goBack() {
        this.onBackPressed();
    }

    override fun finish() {
        val feedback = "Ok, Hello ${this.fullName}. How are you?"

        val data = Intent()
        data.putExtra("feedback", feedback)

        setResult(Activity.RESULT_OK, data)

        super.finish()
    }

}
