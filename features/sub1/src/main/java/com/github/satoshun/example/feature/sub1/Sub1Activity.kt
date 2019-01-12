package com.github.satoshun.example.feature.sub1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.feature.main.AppRouter
import kotlinx.android.synthetic.main.sub1_act.*

class Sub1Activity : AppCompatActivity(),
  Sub1Contract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.sub1_act)

    val presenter = object : Sub1Contract.Presenter {}

    button.setOnClickListener {
      val intent = AppRouter.routeTo(this)
      startActivity(intent)
    }
  }
}
