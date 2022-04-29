package com.walmart.demo.ui.countries_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.walmart.demo.ui.countries_list.adapter.CountryListAdapter
import com.lowe.core.base.BaseFragment
import com.lowe.core.base.provideViewModel
import com.walmart.demo.databinding.FragmentCountryListBinding
import com.walmart.demo.model.Country

class CountryListFragment : BaseFragment<FragmentCountryListBinding, CountryListViewModel>() {
  override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCountryListBinding
    get() = FragmentCountryListBinding::inflate

  override fun obtainViewModel(): CountryListViewModel = provideViewModel(viewModelFactory)

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    val adapter = CountryListAdapter { item ->
      onCountrySelected(item)
    }
    binding.recyclerview.adapter = adapter
    viewModel.listOfCountries.observe(viewLifecycleOwner) {
      adapter.submitList(it)
    }
    viewModel.showProgress.observe(viewLifecycleOwner) { showProgress ->
      if (showProgress) {
        binding.progressBar.visibility = View.VISIBLE
      } else {
        binding.progressBar.visibility = View.GONE
      }
    }

    viewModel.getCountriesList()
  }

  private fun onCountrySelected(item: Country) {
    Toast.makeText(context,"selected ${item.name}",Toast.LENGTH_LONG).show()
  }
}