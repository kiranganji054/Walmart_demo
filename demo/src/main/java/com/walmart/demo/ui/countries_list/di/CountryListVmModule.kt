package com.walmart.demo.ui.countries_list.di

import androidx.lifecycle.ViewModel
import com.lowe.core.ViewModelKey
import com.walmart.demo.ui.countries_list.CountryListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
public interface CountryListVmModule {

  @Binds
  @IntoMap
  @ViewModelKey(CountryListViewModel::class)
  fun bindCountryListViewModel(countryListViewModel: CountryListViewModel): ViewModel
}