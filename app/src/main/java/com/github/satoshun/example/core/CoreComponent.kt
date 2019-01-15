package com.github.satoshun.example.core

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@CoreScope
@Component(modules = [CoreModule::class])
interface CoreComponent {
  @Component.Builder
  abstract class Builder {
    @BindsInstance abstract fun applicationContext(context: Context): Builder
    abstract fun build(): CoreComponent
  }

  val coreItem: CoreItem
}

@Module
class CoreModule {
  @Provides @CoreScope fun provideCoreItem() = CoreItem()
}

class CoreItem
