package com.github.satoshun.example.app

import android.app.Application
import com.github.satoshun.example.CoreComponent
import com.github.satoshun.example.DaggerCoreComponent

class App : Application() {
  companion object {
    private lateinit var appComponent: CoreComponent

    fun appComponent(): CoreComponent = appComponent
  }

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerCoreComponent.builder()
      .applicationContext(this)
      .build()
  }
}
