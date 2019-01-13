package com.github.satoshun.example.feature.main

import android.content.Context
import android.content.Intent
import dagger.Module
import dagger.Provides

@Module
class RouterBuilder {
  @Provides fun provideAppRouter(): AppRouter = AppRouterImpl
  @Provides fun provideMainRouter(): MainRouter = MainRouterImpl
  @Provides fun provideSub1Router(): Sub1Router = Sub1RouterImpl
}

interface AppRouter {
  fun routeTo(context: Context): Intent
}

internal object AppRouterImpl : AppRouter {
  override fun routeTo(context: Context): Intent {
    val packageName = context.packageName
    return Intent(Intent.ACTION_VIEW).setClassName(
      packageName,
      "$packageName.app.AppActivity"
    )
  }
}

interface MainRouter {
  fun routeTo(context: Context): Intent
}

internal object MainRouterImpl : MainRouter {
  override fun routeTo(context: Context): Intent {
    val packageName = context.packageName
    return Intent(Intent.ACTION_VIEW).setClassName(
      packageName,
      "$packageName.feature.main.MainActivity"
    )
  }
}

interface Sub1Router {
  fun routeTo(context: Context): Intent
}

internal object Sub1RouterImpl : Sub1Router {
  override fun routeTo(context: Context): Intent {
    val packageName = context.packageName
    return Intent(Intent.ACTION_VIEW).setClassName(
      packageName,
      "$packageName.feature.sub1.Sub1Activity"
    )
  }
}
