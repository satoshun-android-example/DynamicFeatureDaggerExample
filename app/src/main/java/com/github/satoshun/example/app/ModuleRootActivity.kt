package com.github.satoshun.example.app

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
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

abstract class ModuleChildFragment : Fragment() {
  override fun onAttach(context: Context) {
    ModuleInjection.inject(this)
    super.onAttach(context)
  }
}
