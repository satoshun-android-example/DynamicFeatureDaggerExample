package com.github.satoshun.example.feature.sub1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.sub1_act.*

class Sub1Activity : AppCompatActivity(),
  Sub1Contract.View {

//  @Inject lateinit var router: MainRouter
//  @field:[Inject Named("main")] lateinit var router2: Router<Unit>

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.sub1_act)

    val presenter = object : Sub1Contract.Presenter {}

    button.setOnClickListener {
//      val intent = router.routeToMain(this)
//      val intent = router2.route(this, Unit)
//      startActivity(intent)
    }
  }
}
