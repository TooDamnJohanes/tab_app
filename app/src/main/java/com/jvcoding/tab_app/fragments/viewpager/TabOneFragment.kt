package com.jvcoding.tab_app.fragments.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.jvcoding.tab_app.adapter.MyPager2Adapter
import com.jvcoding.tab_app.databinding.FragmentTabOneBinding
import com.jvcoding.tab_app.utils.TabUtils.requireBinding
import kotlin.random.Random

class TabOneFragment : Fragment() {

    private var _binding: FragmentTabOneBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabOneBinding.inflate(inflater, container, false)
        initViewPager()
        initCircularIndicator()
        return binding.root
    }

    private fun initViewPager() = requireBinding(binding) {
        with(introPager) {
            adapter = MyPager2Adapter(generateNumbers())
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    private fun initCircularIndicator() = requireBinding(binding) {
        circularIndicatorFragmentTabOneTabChanges.setViewPager(introPager)
    }

    private fun generateNumbers(): List<Int> {
        val amountOfComponents = Random.nextInt(5)
        val listOfEvents = mutableListOf<Int>()
        repeat(amountOfComponents) { number ->
            listOfEvents.add(number)
        }
        return listOfEvents
    }


}