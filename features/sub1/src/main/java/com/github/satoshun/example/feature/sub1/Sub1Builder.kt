package com.github.satoshun.example.feature.sub1

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
    Sub1Builder::class,
    RouterBuilder::class
  ]
)
internal interface Sub1Component : ModuleActivityComponent {
  @Component.Builder
  interface Builder {
    fun appComponent(module: CoreComponent): Builder
    fun build(): Sub1Component
  }
}

@Module(
  includes = [Sub1ActivityModule::class]
)
interface Sub1Builder

@Module
internal interface Sub1ActivityModule {
  @ContributesAndroidInjector fun contributeSub1Activity(): Sub1Activity
}
