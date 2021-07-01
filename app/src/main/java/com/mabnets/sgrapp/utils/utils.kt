package com.mabnets.sgrapp.utils

import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()

}


fun Context.showmessages(
    Title: String,
    message: String,
    retry: (() -> Unit)? = null
) {
    AlertDialog.Builder(this).apply {
        setTitle(Title)
        setMessage(message)
        setCancelable(false)
        setPositiveButton("Ok") { _, _ -> retry?.invoke() }
    }.show()
}

fun Context.showPermissionRequestExplanation(
    permission: String,
    message: String,
    retry: (() -> Unit)? = null
) {
    AlertDialog.Builder(this).apply {

        setTitle("$permission Required")
        setMessage(message)
        setPositiveButton("Ok") { _, _ -> retry?.invoke() }
    }.show()
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
fun Context.permissionGranted(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
