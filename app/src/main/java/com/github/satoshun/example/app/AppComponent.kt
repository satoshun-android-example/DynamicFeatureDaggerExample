package com.github.satoshun.example.app

import com.github.satoshun.example.CoreComponent
import com.github.satoshun.example.feature.main.RouterBuilder
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
    AppBuilder::class,
    RouterBuilder::class
  ]
)
internal interface AppComponent : AndroidInjector<AppActivity>,
  ModuleComponent {
  @Component.Builder
  interface Builder {
    fun appComponent(module: CoreComponent): Builder
    fun build(): AppComponent
  }
}

@Module
internal interface AppBuilder {
  @ContributesAndroidInjector
  fun contributeAppActivity(): AppActivity
}
