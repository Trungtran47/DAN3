package com.example.doan3.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.doan3.activity.ReceivedFragment
import com.example.doan3.activity.ProcessingFragment
import com.example.doan3.activity.DeliveringFragment


class TabAdapter(
     fragmentManager: FragmentManager,
     lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return if(position == 0)
            ProcessingFragment()

        else if (position ==1)
            DeliveringFragment()
        else
            ReceivedFragment()
    }

}
