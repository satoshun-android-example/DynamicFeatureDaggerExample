package com.github.satoshun.example.feature.main

import android.os.Bundle
import android.view.View
import com.github.satoshun.example.app.App
import com.github.satoshun.example.app.ModuleComponent
import com.github.satoshun.example.app.ModuleRootActivity
import javax.inject.Inject

class MainActivity : ModuleRootActivity() {
  @Inject lateinit var subRouter: Sub1Router

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    findViewById<View>(R.id.route).setOnClickListener {
      val intent = subRouter.routeTo(this)
      startActivity(intent)
    }

    // test Fragment with Dagger
    supportFragmentManager
      .beginTransaction()
      .add(MainFragment(), "main-fragment")
      .commit()
  }

  override val moduleComponent: ModuleComponent
    get() = DaggerMainAppComponent
      .builder()
      .appComponent(App.appComponent())
      .build()
}
