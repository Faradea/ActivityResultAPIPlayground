package com.example.activityresultapiplayground

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        Log.d(TAG, "Pick file result uri = $uri")
        Toast.makeText(this, "Pick file result uri = $uri", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPickFile = findViewById<Button>(R.id.buttonPickFile)
        buttonPickFile.setOnClickListener {
            // Pass in the mime type you'd like to allow the user to select
            // as the input
            getContent.launch("image/*")
        }

        val buttonPickFileAnim = findViewById<Button>(R.id.buttonPickFileAnim)
        buttonPickFileAnim.setOnClickListener {
            getContent.launch(
                    "image/*",
                    ActivityOptionsCompat
                            .makeScaleUpAnimation(buttonPickFile, 0, 0, 64, 64)
                            //.requestUsageTimeReport()
            )
        }
    }
}