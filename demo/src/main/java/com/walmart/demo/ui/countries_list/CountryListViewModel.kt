package com.walmart.demo.ui.countries_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lowe.core.network.ResultCallback
import com.walmart.demo.model.Country
import com.walmart.demo.repository.CountryRepository
import javax.inject.Inject

class CountryListViewModel @Inject constructor(val repository: CountryRepository) : ViewModel() {

  private val _listOfCountries: MutableLiveData<List<Country>> = MutableLiveData()
  val listOfCountries: LiveData<List<Country>> = _listOfCountries

  private val mShowProgress = MutableLiveData<Boolean>()
  val showProgress: LiveData<Boolean> = mShowProgress

  fun getCountriesList() {
    mShowProgress.value = true
    repository.getCountriesList(object : ResultCallback<List<Country>> {
      override fun onSuccess(data: List<Country>) {
        mShowProgress.value = false
        _listOfCountries.value = data
      }

      override fun onFailure(throwable: Throwable) {
        mShowProgress.value = false
        Log.e("error", throwable!!.localizedMessage)
      }
    })
  }
}