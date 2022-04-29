package com.walmart.demo.di.module

import com.lowe.core.ViewModelModule
import com.walmart.demo.di.network.CountryNetworkModule
import com.walmart.demo.ui.countries_list.di.CountryListModule
import dagger.Module

@Module(includes = [CountryListModule::class, CountryNetworkModule::class, ViewModelModule::class])
interface DemoModule