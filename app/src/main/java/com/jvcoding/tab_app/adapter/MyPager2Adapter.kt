package com.jvcoding.tab_app.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jvcoding.tab_app.fragments.viewpager2.SliderOne
import com.jvcoding.tab_app.fragments.viewpager2.SliderThree
import com.jvcoding.tab_app.fragments.viewpager2.SliderTwo

class MyPager2Adapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            SLIDER_POSITION.ONE.ordinal -> { SliderOne() }
            SLIDER_POSITION.TWO.ordinal -> { SliderTwo() }
            SLIDER_POSITION.THREE.ordinal -> { SliderThree() }
            else -> SliderOne()
        }
    }

    enum class SLIDER_POSITION(value: Int) {
        ONE(0),
        TWO(1),
        THREE(2)
    }
}