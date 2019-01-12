package com.github.satoshun.example.feature.sub1

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
  includes = [Sub1ActivityModule::class]
)
interface Sub1Builder

@Module
internal interface Sub1ActivityModule {
  @ContributesAndroidInjector fun contributeSub1Activity(): Sub1Activity

  @Binds fun bindSub1Router(impl: Sub1RouterImpl): Sub1Router
}
