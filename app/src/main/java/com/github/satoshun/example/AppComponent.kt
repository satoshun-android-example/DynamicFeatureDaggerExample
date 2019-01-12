package com.github.satoshun.example

import android.content.Context
import com.github.satoshun.example.app.App
import dagger.BindsInstance
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@dagger.Component(
  modules = [
    AndroidSupportInjectionModule::class
//    MainBuilder::class, // todo
//    Sub1Builder::class
  ]
)
interface AppComponent : AndroidInjector<App> {
  @dagger.Component.Builder
  abstract class Builder : AndroidInjector.Builder<App>() {
    @BindsInstance abstract fun applicationContext(context: Context): Builder
  }
}
