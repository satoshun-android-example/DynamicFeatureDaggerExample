package com.github.satoshun.example.app

import android.os.Bundle
import com.github.satoshun.example.feature.main.MainRouter
import kotlinx.android.synthetic.main.app_act.*
import javax.inject.Inject

class AppActivity : ModuleRootActivity() {
  @Inject lateinit var mainRouter: MainRouter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.app_act)
    button.setOnClickListener {
      val intent = mainRouter.routeTo(this)
      startActivity(intent)
    }

    // test Fragment with Dagger
    supportFragmentManager
      .beginTransaction()
      .add(AppFragment(), "app-Fragment")
      .commit()
  }

  override val moduleComponent: ModuleComponent
    get() = DaggerAppComponent
      .builder()
      .appComponent(App.appComponent())
      .build()
}
