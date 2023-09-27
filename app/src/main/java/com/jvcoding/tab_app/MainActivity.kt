package com.jvcoding.tab_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jvcoding.tab_app.adapter.MyPagerAdapter
import com.jvcoding.tab_app.databinding.ActivityMainBinding
import com.jvcoding.tab_app.utils.TabUtils.requireBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPagerAdapter()
        initTabLayout()
    }

    private fun initViewPagerAdapter() = requireBinding(binding) {
        viewPagerMainActivityViewPager.adapter = MyPagerAdapter(supportFragmentManager)
    }

    private fun initTabLayout() = requireBinding(binding) {
        tabLayoutMainActivityTabLayout.setupWithViewPager(viewPagerMainActivityViewPager)
    }
}