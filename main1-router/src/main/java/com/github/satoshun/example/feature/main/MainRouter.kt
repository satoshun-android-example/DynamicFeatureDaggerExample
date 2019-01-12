package com.github.satoshun.example.feature.main

import android.content.Context
import android.content.Intent

interface MainRouter {
  fun routeToMain(context: Context): Intent
}
