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
