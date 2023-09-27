package com.jvcoding.tab_app.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jvcoding.tab_app.fragments.viewpager.TabOneFragment
import com.jvcoding.tab_app.fragments.viewpager.TabThreeFragment
import com.jvcoding.tab_app.fragments.viewpager.TabTwoFragment

class MyPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            TAB_POSITION.ONE.ordinal -> { TabOneFragment() }
            TAB_POSITION.TWO.ordinal -> { TabTwoFragment() }
            TAB_POSITION.THREE.ordinal -> { TabThreeFragment() }
            else -> TabOneFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            TAB_POSITION.ONE.ordinal -> "TabOne"
            TAB_POSITION.TWO.ordinal -> "Tab Two"
            TAB_POSITION.THREE.ordinal -> "Tab Three"
            else -> "TabOne"
        }
    }

    enum class TAB_POSITION(value: Int) {
        ONE(0),
        TWO(1),
        THREE(2)
    }

}