package com.github.satoshun.example.app

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
    AppBuilder::class,
    RouterBuilder::class
  ]
)
internal interface AppComponent : ModuleActivityComponent {
  @Component.Builder
  interface Builder {
    fun appComponent(module: CoreComponent): Builder
    fun build(): AppComponent
  }
}

@Module
internal interface AppBuilder {
  @ContributesAndroidInjector(
    modules = [
      AppActivityModule::class
    ]
  )
  fun contributeAppActivity(): AppActivity
}

@Module
internal interface AppActivityModule {
  @ContributesAndroidInjector fun contributeAppFragme(): AppFragment
}
