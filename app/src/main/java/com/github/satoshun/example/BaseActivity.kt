package com.github.satoshun.example

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseActivity : AppCompatActivity(),
  CoroutineScope {
  private val job = Job()
  override val coroutineContext get() = job + Dispatchers.Main
}
