package com.github.satoshun.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.feature.main.MainRouter
import kotlinx.android.synthetic.main.app_act.*

class AppActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.app_act)
    button.setOnClickListener {
      val intent = MainRouter.routeTo(this)
//      val intent = Sub1Router.routeTo(this)
      startActivity(intent)
    }
  }
}
