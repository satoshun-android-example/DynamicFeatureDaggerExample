package com.github.satoshun.example.feature.main

import android.os.Bundle
import android.view.View
import com.github.satoshun.example.app.App
import com.github.satoshun.example.core.CoreItem
import com.github.satoshun.example.di.ModuleActivityComponent
import com.github.satoshun.example.di.ModuleRootActivity
import com.github.satoshun.example.router.Sub1Router
import javax.inject.Inject

class MainActivity : ModuleRootActivity() {
  @Inject lateinit var subRouter: Sub1Router
  @Inject lateinit var coreItem: CoreItem

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

  override val moduleComponent: ModuleActivityComponent
    get() = DaggerMainAppComponent
      .builder()
      .appComponent(App.coreComponent())
      .build()
}
