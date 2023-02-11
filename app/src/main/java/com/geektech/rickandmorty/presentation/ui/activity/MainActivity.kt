package com.geektech.rickandmorty.presentation.ui.activity

import android.view.LayoutInflater
import com.geektech.rickandmorty.core.base.BaseActivity
import com.geektech.rickandmorty.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initialize() {

    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }
}