package com.github.satoshun.example.feature.sub1

import android.os.Bundle
import com.github.satoshun.example.app.App
import com.github.satoshun.example.di.ModuleComponent
import com.github.satoshun.example.di.ModuleRootActivity
import com.github.satoshun.example.router.AppRouter
import kotlinx.android.synthetic.main.sub1_act.*
import javax.inject.Inject

class Sub1Activity : ModuleRootActivity(),
  Sub1Contract.View {
  @Inject lateinit var appRouter: AppRouter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.sub1_act)

    val presenter = object : Sub1Contract.Presenter {}

    button.setOnClickListener {
      val intent = appRouter.routeTo(this)
      startActivity(intent)
    }
  }

  override val moduleComponent: ModuleComponent
    get() = DaggerSub1Component
      .builder()
      .appComponent(App.appComponent())
      .build()
}
