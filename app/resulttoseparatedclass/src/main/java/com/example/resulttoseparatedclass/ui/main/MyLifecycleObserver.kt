package com.example.resulttoseparatedclass.ui.main

import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyLifecycleObserver(private val registry : ActivityResultRegistry)
    : DefaultLifecycleObserver {
    lateinit var getContent : ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        var callback: ActivityResultCallback<Uri> = ActivityResultCallback { uri ->
            Log.d("MyLifecycleObserver", "Pick file result uri = $uri")
        }
        getContent = registry.register("key", owner, ActivityResultContracts.GetContent(), callback)

        // When using the ActivityResultRegistry APIs, it's strongly recommended to use the APIs that take a LifecycleOwner,
        // as the LifecycleOwner automatically removes your registered launcher when the Lifecycle is destroyed.
        // However, in cases where a LifecycleOwner is not available, each ActivityResultLauncher class
        // allows you to manually call unregister() as an alternative.
        // getContent.unregister()
    }

    fun selectImage() {
        getContent.launch("image/*")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }
}