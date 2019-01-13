package com.github.satoshun.example.feature.main

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector

abstract class RootActivity : AppCompatActivity() {
  lateinit var injector: DispatchingAndroidInjector<Activity>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }
}
