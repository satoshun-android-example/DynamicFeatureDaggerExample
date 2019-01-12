package com.github.satoshun.example.feature.main

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

@Module(
  includes = [MainActivityModule::class]
)
interface MainBuilder

@Module
internal interface MainActivityModule {
  @ContributesAndroidInjector fun contributeMainActivity(): MainActivity

  @Binds fun bindMainRouter(impl: MainRouterImpl): MainRouter
  @Named("main")
  @Binds fun bindMain2Router(impl: MainRouter2Impl): Router<Unit>
}
