package com.lowe.walmart.di

import android.app.Application
import com.lowe.core.network.NetworkModule
import com.walmart.demo.di.module.DemoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    WalmartAppModule::class,
    NetworkModule::class,
    DemoModule::class]
)
interface WalmartComponent : AndroidInjector<WalmartApplication> {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun withApplication(application: Application): Builder
    fun build(): WalmartComponent
  }
}