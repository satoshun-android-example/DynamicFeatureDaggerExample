package com.github.satoshun.example.feature.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
//    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    findViewById<View>(R.id.route).setOnClickListener {
      val intent = Sub1Router.routeTo(this)
      startActivity(intent)
    }
  }
}
