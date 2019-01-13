package com.github.satoshun.example.app

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.feature.main.MainRouter
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.app_act.*
import javax.inject.Inject

class AppActivity : AppCompatActivity() {
  // todo it's a root component of App module
  private lateinit var dispatching: DispatchingAndroidInjector<Activity>

  @Inject lateinit var mainRouter: MainRouter

  override fun onCreate(savedInstanceState: Bundle?) {
    val injector = DaggerAppComponent
      .builder()
      .appComponent(App.appComponent())
      .create(this) as AppComponent
    dispatching = injector.dispatching

    dispatching.inject(this)
    super.onCreate(savedInstanceState)

    setContentView(R.layout.app_act)
    button.setOnClickListener {
      val intent = mainRouter.routeTo(this)
      startActivity(intent)
    }
  }
}
