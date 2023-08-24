package com.sunnyweather.android.logic.util
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

object WindowHelper {
    fun Activity.immerseSystemBars(
        window: Window,
        fullScreen: Boolean = false,
        dark: Boolean = false
    ) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.navigationBarDividerColor = Color.TRANSPARENT
        }
        WindowCompat.getInsetsController(
            window,
            findViewById(android.R.id.content)
        ).let { controller ->
            if (fullScreen) {
                controller.hide(WindowInsetsCompat.Type.statusBars())
            } else {
                controller.show(WindowInsetsCompat.Type.statusBars())
            }
            controller.isAppearanceLightStatusBars = !dark
        }
    }



}