package com.github.satoshun.example.app

import com.github.satoshun.example.feature.main.MainRouter
import javax.inject.Inject

class AppFragment : ModuleChildFragment() {
  @Inject lateinit var mainRouter: MainRouter
}
