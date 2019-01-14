package com.github.satoshun.example.app

import com.github.satoshun.example.di.ModuleChildFragment
import com.github.satoshun.example.router.MainRouter
import javax.inject.Inject

class AppFragment : ModuleChildFragment() {
  @Inject lateinit var mainRouter: MainRouter
}
