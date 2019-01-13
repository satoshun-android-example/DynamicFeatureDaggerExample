package com.github.satoshun.example.feature.main

import com.github.satoshun.example.CoreComponent
import com.github.satoshun.example.app.ModuleComponent
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
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
internal interface MainAppComponent : AndroidInjector<MainActivity>,
  ModuleComponent {
  @Component.Builder
  abstract class Builder {
    abstract fun appComponent(module: CoreComponent): Builder

    abstract fun build(): MainAppComponent
  }
}

@Module(
  includes = [MainActivityModule::class]
)
internal interface MainBuilder

@Module
internal interface MainActivityModule {
  @ContributesAndroidInjector fun contributeMainActivity(): MainActivity
}
