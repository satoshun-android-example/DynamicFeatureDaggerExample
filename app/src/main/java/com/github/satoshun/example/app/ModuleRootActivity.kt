package com.github.satoshun.example.app

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

abstract class ModuleRootActivity : AppCompatActivity(),
  HasModuleInjector {
  private lateinit var injector: ModuleInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    injector = moduleComponent.moduleInjector
    injector.activity.inject(this)
    super.onCreate(savedInstanceState)
  }

  protected abstract val moduleComponent: ModuleComponent

  override fun activityInjector(): AndroidInjector<Activity> = injector.activity
}

class ModuleInjector @Inject constructor(
  internal val activity: DispatchingAndroidInjector<Activity>
)

interface ModuleComponent {
  val moduleInjector: ModuleInjector
}

// todo Fragment, Service and others?
interface HasModuleInjector : HasActivityInjector
