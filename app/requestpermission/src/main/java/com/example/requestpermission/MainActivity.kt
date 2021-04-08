package com.example.requestpermission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var buttonSinglePermission: Button
    private lateinit var buttonMultiplePermission: Button

    // Single Permission Contract
    private val askLocationPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        if(result){
            Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Location permission NOT granted", Toast.LENGTH_SHORT).show()
        }
    }

    // Multiple Permissions Contract
    private val askMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {map ->
        for (entry in map.entries)
        {
            Toast.makeText(this, "${entry.key} = ${entry.value}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSinglePermission = findViewById(R.id.buttonSinglePermission)
        buttonMultiplePermission = findViewById(R.id.buttonMultiplePermission)

        buttonSinglePermission.setOnClickListener {
            askLocationPermission.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }

        buttonMultiplePermission.setOnClickListener {
            askMultiplePermissions.launch(arrayOf(
                    android.Manifest.permission.READ_CONTACTS,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            ))
        }
    }
}