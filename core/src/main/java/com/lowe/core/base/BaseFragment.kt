package com.lowe.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding, V : ViewModel> : DaggerFragment() {

  private var _binding: B? = null
  protected val binding get() = requireNotNull(_binding)

  abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B

  @Inject
  lateinit var viewModelFactory: Factory

  protected lateinit var viewModel: V

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = obtainViewModel()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = bindingInflater.invoke(inflater, container, false)
    val onBackPressedCallback = object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        findNavController().navigateUp()
      }
    }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    return binding.root
  }

  @CallSuper
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }



  abstract fun obtainViewModel(): V

}