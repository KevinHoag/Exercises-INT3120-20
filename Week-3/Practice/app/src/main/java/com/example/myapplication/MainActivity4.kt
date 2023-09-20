package com.example.practice

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

//        val phone = Intent(Intent.ACTION_DIAL, Uri.parse("tel:555-1234"))
//        startActivity(phone)

//        val webSearch = Intent(Intent.ACTION_WEB_SEARCH);
//        webSearch.putExtra(SearchManager.QUERY, "straight hitting golf clubs")
//        startActivity(webSearch)

//        val sendIntent = Intent()
//        sendIntent.action = Intent.ACTION_SEND
//        sendIntent.type = "text/plain"
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello, this is the message")
//        sendIntent.addCategory(Intent.CATEGORY_DEFAULT)
//        sendIntent.component = ComponentName("com.example.practice", "com.example.practice.MainActivity3")
//        startActivity(sendIntent)

        val sendTo = Intent(Intent.ACTION_SENDTO, Uri.parse("sms:5551234"))
        sendTo.putExtra("sms_body", "are we playing golf next Saturday?")
        startActivity(sendTo)

//        val image = Intent()
//
//        image.type = "image/pictures/*"
//        image.action = Intent.ACTION_GET_CONTENT
//
//        startActivity(image)

//        val myData = "content://contacts/people/"
//
//        val myActivity2 = Intent(
//            Intent.ACTION_VIEW,
//            Uri.parse(myData)
//        )
//
//        startActivity(myActivity2)

        // Map
//        val geoCode = "geo:0,0?q=1860+east+18th+street+cleveland+oh"
//
//        val intent = Intent(
//            Intent.ACTION_VIEW,
//            Uri.parse(geoCode)
//        )
//
//        startActivity(intent)

        // Music: not resolved
//        val intent = Intent(
//            Intent.ACTION_VIEW,
//            Uri.parse("android.intent.action.MUSIC_PLAYER")
//        )
//        startActivity(intent)


        val backButton = findViewById<Button>(R.id.go_back)
        backButton.setOnClickListener {
            this.onBackPressed();
        }
    }
}