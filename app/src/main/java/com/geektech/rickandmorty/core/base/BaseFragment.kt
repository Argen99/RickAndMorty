package com.geektech.rickandmorty.core.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagingData
import androidx.viewbinding.ViewBinding
import com.geektech.rickandmorty.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment<Binding : ViewBinding, ViewModel : BaseViewModel>(
    @LayoutRes private val layoutRes: Int
) : Fragment(layoutRes) {

    protected abstract val binding: Binding
    protected abstract val viewModel: ViewModel
    private var _navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        _navController = navHostFragment.navController

        checkInternetConnection()
        initialize()
        setupRequest()
        performListeners()
        setupObservers()
        setupClickListeners()
    }

    protected open fun checkInternetConnection() {}
    protected open fun initialize() {}
    protected open fun setupRequest() {}
    protected open fun performListeners() {}
    protected open fun setupObservers() {}
    protected open fun setupClickListeners() {}

    protected fun navigate(direction: Int, data: Bundle? = null) {
        _navController?.navigate(direction, data)
    }

    protected fun navigateUp() {
        _navController?.navigateUp()
    }
}