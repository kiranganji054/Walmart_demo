package com.lowe.walmart.di

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class WalmartApplication : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerWalmartComponent.builder()
      .withApplication(this)
      .build()
  }
}