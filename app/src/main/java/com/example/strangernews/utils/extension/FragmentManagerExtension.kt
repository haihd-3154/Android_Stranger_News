package com.example.strangernews.utils.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.strangernews.R

fun FragmentManager.replaceFragment(
    container: Int,
    fragment: Fragment,
    backStack: Boolean = false
) {
    beginTransaction().apply {
        setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        )
        replace(container, fragment)
        if (backStack) addToBackStack(null)
    }.commit()
}