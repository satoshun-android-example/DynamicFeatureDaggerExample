package com.github.satoshun.example.feature.main

import com.github.satoshun.example.di.ModuleChildFragment
import com.github.satoshun.example.router.AppRouter
import javax.inject.Inject

class MainFragment : ModuleChildFragment() {
  @Inject lateinit var router: AppRouter
}
