package com.github.satoshun.example.app

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.app_act.*

class AppActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.app_act)
    button.setOnClickListener {
    }
  }
}
