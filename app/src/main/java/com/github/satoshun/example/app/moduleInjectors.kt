package com.github.satoshun.example.app

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class ModuleInjector @Inject constructor(
  internal val activity: DispatchingAndroidInjector<Activity>
)

interface ModuleComponent {
  val moduleInjector: ModuleInjector
}

interface HasModuleInjector : HasSupportFragmentInjector

object ModuleInjection {
  fun inject(fragment: Fragment) {
    val injector = findHasFragmentInjector(fragment)
      .supportFragmentInjector()
    injector.inject(fragment)
  }

  private fun findHasFragmentInjector(
    fragment: Fragment
  ): HasSupportFragmentInjector {
    var parentFragment: Fragment? = fragment

    do {
      parentFragment = parentFragment?.parentFragment
      if (parentFragment == null) {
        val activity = fragment.activity
        if (activity is HasSupportFragmentInjector) {
          return activity
        }
        throw IllegalArgumentException(
          String.format(
            "No injector was found for %s",
            fragment.javaClass.canonicalName
          )
        )
      }
    } while (parentFragment !is HasSupportFragmentInjector)
    return parentFragment
  }
}
