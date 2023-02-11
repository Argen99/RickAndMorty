package com.geektech.rickandmorty.presentation.ui.activity

import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.rickandmorty.core.base.BaseActivity
import com.geektech.rickandmorty.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val binding by viewBinding(ActivityMainBinding::bind)

    override fun initialize() {

    }
}