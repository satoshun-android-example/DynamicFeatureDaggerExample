package com.github.satoshun.example

import android.content.Context
import com.github.satoshun.example.feature.main.MainBuilder
import com.github.satoshun.example.feature.sub1.Sub1Builder
import dagger.BindsInstance
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@dagger.Component(
  modules = [
    AndroidSupportInjectionModule::class,
    MainBuilder::class,
    Sub1Builder::class
  ]
)
interface AppComponent : AndroidInjector<App> {
  @dagger.Component.Builder
  abstract class Builder : AndroidInjector.Builder<App>() {
    @BindsInstance abstract fun applicationContext(context: Context): Builder
  }
}
