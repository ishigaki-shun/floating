package com.example.s_ishigaki.floatingscreen

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings

fun Activity.hasOverlayPermission(): Boolean = Settings.canDrawOverlays(this)

fun Activity.requestOverlayPermission(requestCode: Int) {
    val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
    startActivityForResult(intent, requestCode)
}
