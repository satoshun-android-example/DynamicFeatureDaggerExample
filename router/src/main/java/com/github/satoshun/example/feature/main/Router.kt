package com.github.satoshun.example.feature.main

import android.content.Context
import android.content.Intent

interface Router<T> {
  fun route(context: Context, params: T): Intent
}
