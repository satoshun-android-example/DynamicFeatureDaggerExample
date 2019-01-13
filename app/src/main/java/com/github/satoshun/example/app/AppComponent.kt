package com.github.satoshun.example.app

import android.app.Activity
import com.github.satoshun.example.CoreComponent
import com.github.satoshun.example.feature.main.RouterBuilder
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
    AppBuilder::class,
    RouterBuilder::class
  ]
)
internal interface AppComponent : AndroidInjector<AppActivity> {
  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<AppActivity>() {
    abstract fun appComponent(module: CoreComponent): Builder
  }

  val dispatching: DispatchingAndroidInjector<Activity>
}

@Module
internal interface AppBuilder {
  @ContributesAndroidInjector
  fun contributeAppActivity(): AppActivity
}
