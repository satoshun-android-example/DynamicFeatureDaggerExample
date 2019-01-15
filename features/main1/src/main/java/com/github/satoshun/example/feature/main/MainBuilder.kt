package com.github.satoshun.example.feature.main

import com.github.satoshun.example.core.CoreComponent
import com.github.satoshun.example.di.ModuleActivityComponent
import com.github.satoshun.example.di.ModuleScope
import com.github.satoshun.example.router.RouterBuilder
import dagger.Component
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ModuleScope
@Component(
  dependencies = [CoreComponent::class],
  modules = [
    AndroidSupportInjectionModule::class,
    MainBuilder::class,
    RouterBuilder::class
  ]
)
internal interface MainAppComponent : ModuleActivityComponent {
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
