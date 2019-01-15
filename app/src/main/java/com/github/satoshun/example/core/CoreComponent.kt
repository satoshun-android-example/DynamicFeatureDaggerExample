package com.github.satoshun.example.core

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component
interface CoreComponent {
  @Component.Builder
  abstract class Builder {
    @BindsInstance abstract fun applicationContext(context: Context): Builder
    abstract fun build(): CoreComponent
  }
}
