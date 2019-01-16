package com.github.satoshun.example.di

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import javax.inject.Scope

abstract class ModuleRootActivity : AppCompatActivity(),
  HasModuleInjector {
  @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  private lateinit var injector: ModuleActivityInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    injector = moduleComponent.moduleInjector
    injector.activity.inject(this)
    super.onCreate(savedInstanceState)
  }

  protected abstract val moduleComponent: ModuleActivityComponent

  override fun supportFragmentInjector(): AndroidInjector<Fragment> =
    fragmentInjector
}

class ModuleActivityInjector @Inject constructor(
  internal val activity: DispatchingAndroidInjector<Activity>
)

interface ModuleActivityComponent {
  val moduleInjector: ModuleActivityInjector
}

abstract class ModuleRootFragment : Fragment(),
  HasModuleInjector {
  @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  private lateinit var injector: ModuleFragmentInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    injector = moduleComponent.moduleInjector
    injector.fragment.inject(this)
    super.onCreate(savedInstanceState)
  }

  protected abstract val moduleComponent: ModuleFragmentComponent

  override fun supportFragmentInjector(): AndroidInjector<Fragment> =
    fragmentInjector
}

class ModuleFragmentInjector @Inject constructor(
  internal val fragment: DispatchingAndroidInjector<Fragment>
)

interface ModuleFragmentComponent {
  val moduleInjector: ModuleFragmentInjector
}

abstract class ModuleChildFragment : Fragment() {
  override fun onAttach(context: Context) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }
}

interface HasModuleInjector : HasSupportFragmentInjector

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ModuleScope
