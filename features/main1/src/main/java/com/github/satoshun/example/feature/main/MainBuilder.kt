package com.github.satoshun.example.feature.main

import android.app.Activity
import com.github.satoshun.example.CoreComponent
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  dependencies = [CoreComponent::class],
  modules = [
    AndroidSupportInjectionModule::class,
    MainBuilder::class,
    RouterBuilder::class
  ]
)
internal interface MainAppComponent : AndroidInjector<MainActivity> {
  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<MainActivity>() {
    abstract fun appComponent(module: CoreComponent): Builder
  }

  val dispatching: DispatchingAndroidInjector<Activity>
}

@Module(
  includes = [MainActivityModule::class]
)
internal interface MainBuilder

@Module
internal interface MainActivityModule {
  @ContributesAndroidInjector fun contributeMainActivity(): MainActivity
}
