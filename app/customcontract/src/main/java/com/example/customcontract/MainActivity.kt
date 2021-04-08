package com.example.customcontract

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var tvText: TextView
    private lateinit var buttonEdit: Button
    private lateinit var buttonEditGeneric: Button

    private val customContractRegistration = registerForActivityResult(MyActivityResultContract()) { resultStr ->
        resultStr?.let {
            tvText.text = it
        }
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            tvText.text = result.data?.getStringExtra(MyActivityResultContract.RESULT_EXTRA_TEXT_KEY)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.tvText)
        buttonEdit = findViewById(R.id.buttonEdit)
        buttonEditGeneric = findViewById(R.id.buttonEditGeneric)

        buttonEdit.setOnClickListener {
            customContractRegistration.launch(tvText.text.toString())
        }

        buttonEditGeneric.setOnClickListener {
            startForResult.launch(Intent(this, EditTextActivity::class.java)
                    .putExtra(MyActivityResultContract.INTENT_EXTRA_TEXT_KEY, tvText.text.toString()))
        }
    }
}