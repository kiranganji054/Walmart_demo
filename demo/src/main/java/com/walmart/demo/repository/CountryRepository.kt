package com.walmart.demo.repository

import com.lowe.core.network.ResultCallback
import com.walmart.demo.model.Country

interface CountryRepository {
  fun getCountriesList(callBack: ResultCallback<List<Country>>)
}