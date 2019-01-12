package com.github.satoshun.example.feature.sub1

import android.content.Context
import android.content.Intent
import javax.inject.Inject

internal class Sub1RouterImpl @Inject constructor() : Sub1Router {
  override fun routeToSub1(context: Context): Intent {
    return Intent(context, Sub1Activity::class.java)
  }
}
