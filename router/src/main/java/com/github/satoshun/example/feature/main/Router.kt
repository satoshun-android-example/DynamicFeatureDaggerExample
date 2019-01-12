package com.github.satoshun.example.feature.main

import android.content.Context
import android.content.Intent

// todo more readable

object AppRouter {
  fun routeTo(context: Context): Intent {
    val packageName = context.packageName
    return Intent(Intent.ACTION_VIEW).setClassName(
      packageName,
      "$packageName.app.AppActivity"
    )
  }
}

object MainRouter {
  fun routeTo(context: Context): Intent {
    val packageName = context.packageName
    return Intent(Intent.ACTION_VIEW).setClassName(
      packageName,
      "$packageName.feature.main.MainActivity"
    )
  }
}

object Sub1Router {
  fun routeTo(context: Context): Intent {
    val packageName = context.packageName
    return Intent(Intent.ACTION_VIEW).setClassName(
      packageName,
      "$packageName.feature.sub1.Sub1Activity"
    )
  }
}
