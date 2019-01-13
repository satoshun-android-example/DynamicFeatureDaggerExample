package com.github.satoshun.example.feature.sub1

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.app.App
import com.github.satoshun.example.feature.main.AppRouter
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.sub1_act.*
import javax.inject.Inject

class Sub1Activity : AppCompatActivity(),
  Sub1Contract.View {

  // todo it's a root component of App module
  private lateinit var dispatching: DispatchingAndroidInjector<Activity>

  @Inject lateinit var appRouter: AppRouter

  override fun onCreate(savedInstanceState: Bundle?) {
    val injector = DaggerSub1Component
      .builder()
      .appComponent(App.appComponent())
      .create(this) as Sub1Component
    dispatching = injector.dispatching

    dispatching.inject(this)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.sub1_act)

    val presenter = object : Sub1Contract.Presenter {}

    button.setOnClickListener {
      val intent = appRouter.routeTo(this)
      startActivity(intent)
    }
  }
}
