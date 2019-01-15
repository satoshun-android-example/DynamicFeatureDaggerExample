package com.github.satoshun.example.app

import android.app.Application
import com.github.satoshun.example.core.CoreComponent
import com.github.satoshun.example.core.DaggerCoreComponent

class App : Application() {
  companion object {
    private lateinit var coreComponent: CoreComponent

    fun coreComponent(): CoreComponent = coreComponent
  }

  override fun onCreate() {
    super.onCreate()
    coreComponent = DaggerCoreComponent.builder()
      .applicationContext(this)
      .build()
  }
}
