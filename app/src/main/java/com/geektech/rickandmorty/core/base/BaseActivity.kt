package com.geektech.rickandmorty.core.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.geektech.rickandmorty.R

abstract class BaseActivity<Binding : ViewBinding>: AppCompatActivity() {

    protected lateinit var binding: Binding
    protected abstract fun inflateViewBinding(inflater: LayoutInflater): Binding
    private var _navController: NavController? = null
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = inflateViewBinding(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        _navController = navHostFragment.navController
        navController = _navController as NavController

        initialize()
    }

    protected open fun initialize() {}
}