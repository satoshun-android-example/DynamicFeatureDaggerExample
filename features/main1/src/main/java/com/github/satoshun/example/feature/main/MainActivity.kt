package com.github.satoshun.example.feature.main

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.app.App
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
  // todo it's a root component of App module
  private lateinit var dispatching: DispatchingAndroidInjector<Activity>

  @Inject lateinit var subRouter: Sub1Router

  override fun onCreate(savedInstanceState: Bundle?) {
    val injector = DaggerMainAppComponent
      .builder()
      .appComponent(App.appComponent())
      .create(this) as MainAppComponent
    dispatching = injector.dispatching
    dispatching.inject(this)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    findViewById<View>(R.id.route).setOnClickListener {
      val intent = subRouter.routeTo(this)
      startActivity(intent)
    }
  }
}
