package com.github.satoshun.example.feature.main

import com.github.satoshun.example.CoreComponent
import com.github.satoshun.example.app.ModuleComponent
import dagger.Component
import dagger.Module
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
internal interface MainAppComponent : ModuleComponent {
  @Component.Builder
  interface Builder {
    fun appComponent(module: CoreComponent): Builder
    fun build(): MainAppComponent
  }
}

@Module(
  includes = [MainActivityModule::class]
)
internal interface MainBuilder

@Module
internal interface MainActivityModule {
  @ContributesAndroidInjector(
    modules = [MainFragmentModule::class]
  )
  fun contributeMainActivity(): MainActivity
}

@Module
internal interface MainFragmentModule {
  @ContributesAndroidInjector
  fun contributeMainFragment(): MainFragment
}
