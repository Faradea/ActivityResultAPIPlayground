package com.example.customcontract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class MyActivityResultContract : ActivityResultContract<String, String?>() {

    companion object {
        const val INTENT_EXTRA_TEXT_KEY = "text"
        const val RESULT_EXTRA_TEXT_KEY = "newText"
    }

    override fun createIntent(context: Context, text: String): Intent {
        return Intent(context, EditTextActivity::class.java)
            .putExtra(INTENT_EXTRA_TEXT_KEY, text)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return when {
            resultCode != Activity.RESULT_OK -> null      // Return null, if action is cancelled
            else -> intent?.getStringExtra(RESULT_EXTRA_TEXT_KEY)
        }
    }
}