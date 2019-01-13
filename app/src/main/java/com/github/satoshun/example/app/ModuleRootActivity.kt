package com.github.satoshun.example.app

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class ModuleRootActivity : AppCompatActivity(),
  HasModuleInjector {
  @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  private lateinit var injector: ModuleInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    injector = moduleComponent.moduleInjector
    injector.activity.inject(this)
    super.onCreate(savedInstanceState)
  }

  protected abstract val moduleComponent: ModuleComponent

  override fun supportFragmentInjector(): AndroidInjector<Fragment> =
    fragmentInjector
}

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
