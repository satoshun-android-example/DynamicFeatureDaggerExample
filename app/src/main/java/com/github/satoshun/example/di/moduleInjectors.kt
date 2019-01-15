package com.github.satoshun.example.di

import android.app.Activity
import android.content.Context
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
    ModuleInjection.inject(this)
    super.onAttach(context)
  }
}

interface HasModuleInjector : HasSupportFragmentInjector

/*
 * Copyright (C) 2017 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
          "No injector was found for ${fragment.javaClass.canonicalName}"
        )
      }
    } while (parentFragment !is HasSupportFragmentInjector)
    return parentFragment
  }
}
