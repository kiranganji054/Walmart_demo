package com.walmart.demo.ui.countries_list.di

import com.lowe.core.scopes.FragmentScope
import com.walmart.demo.ui.countries_list.CountryListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface CountryListModule {

  @FragmentScope
  @ContributesAndroidInjector(modules = [CountryListVmModule::class])
  fun contributeCountryListInjector(): CountryListFragment
}