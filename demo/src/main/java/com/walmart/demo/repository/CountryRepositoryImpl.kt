package com.walmart.demo.repository

import com.lowe.core.network.ResultCallback
import com.walmart.demo.di.network.CountriesApi
import com.walmart.demo.model.CountriesList
import com.walmart.demo.model.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * [CountryRepository] implementation for Country endpoints.
 */
class CountryRepositoryImpl @Inject constructor(private val countriesApi: CountriesApi) :
  CountryRepository {
  override fun getCountriesList(callBack: ResultCallback<List<Country>>) {
    val call: Call<CountriesList> =
      countriesApi.getCountries()
    call.enqueue(object : Callback<CountriesList> {
      override fun onResponse(
        call: Call<CountriesList>,
        response: Response<CountriesList>
      ) {
        val countriesList = response.body()
        callBack.onSuccess(countriesList)
      }

      override fun onFailure(
        call: Call<CountriesList>,
        t: Throwable
      ) {
        callBack.onFailure(t)
      }
    })
  }
}