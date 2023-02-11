package com.geektech.rickandmorty

import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.rickandmorty.core.BaseActivity
import com.geektech.rickandmorty.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val binding by viewBinding(ActivityMainBinding::bind)
}