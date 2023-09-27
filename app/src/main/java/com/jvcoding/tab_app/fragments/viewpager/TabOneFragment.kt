package com.jvcoding.tab_app.fragments.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.jvcoding.tab_app.adapter.MyPager2Adapter
import com.jvcoding.tab_app.databinding.FragmentTabOneBinding
import com.jvcoding.tab_app.utils.TabUtils.requireBinding

class TabOneFragment : Fragment() {

    private var _binding: FragmentTabOneBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabOneBinding.inflate(inflater, container, false)
        initViewPagerAdapter()
        initTabLayout()
        return binding.root
    }

    private fun initViewPagerAdapter() = requireBinding(binding) {
        introPager.adapter = MyPager2Adapter(requireActivity())
    }

    private fun initTabLayout() = requireBinding(binding) {
        TabLayoutMediator(intoTabLayout, introPager) { tab, position -> }.attach()
    }


}