package com.walmart.demo.di.network

import android.content.Context
import androidx.annotation.NonNull
import com.lowe.core.network.ServiceFactory
import com.walmart.demo.repository.CountryRepository
import com.walmart.demo.repository.CountryRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CountryNetworkModule {

  @Provides
  @NonNull
  fun provideCountryService(
    @NonNull context: Context,
    @NonNull serviceFactory: ServiceFactory
  ): CountriesApi {
    val baseUrl = "https://gist.githubusercontent.com/"
    return serviceFactory.create(baseUrl, CountriesApi::class.java)
  }
  @Provides
  @Singleton
  @NonNull
  fun provideRepositoryImpl(repository: CountryRepositoryImpl): CountryRepository {
      return repository
  }
}