package com.example.customcontract

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class EditTextActivity : AppCompatActivity() {

    private lateinit var input: TextInputLayout
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)

        input = findViewById(R.id.textInputLayout)
        saveButton = findViewById(R.id.buttonSave)

        input.editText?.setText(intent.getStringExtra(MyActivityResultContract.INTENT_EXTRA_TEXT_KEY))

        saveButton.setOnClickListener {
            input.editText?.text?.toString()?.let {
                val data = Intent().apply {
                    putExtra(MyActivityResultContract.RESULT_EXTRA_TEXT_KEY, it)
                }
                setResult(Activity.RESULT_OK, data)
            } ?: run {
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        }
    }
}