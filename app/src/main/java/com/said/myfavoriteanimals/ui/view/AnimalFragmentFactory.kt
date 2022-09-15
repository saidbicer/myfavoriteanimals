package com.said.myfavoriteanimals.ui.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.said.myfavoriteanimals.ui.adapter.FavoriteAnimalsAdapter
import com.said.myfavoriteanimals.util.PreferencesUtils
import javax.inject.Inject


class AnimalFragmentFactory @Inject constructor(
    private val favoriteAnimalsAdapter: FavoriteAnimalsAdapter,
    private val preferencesUtils: PreferencesUtils
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            FavoriteAnimalsFragment::class.java.name -> FavoriteAnimalsFragment(favoriteAnimalsAdapter)
            AnimalFragment::class.java.name -> AnimalFragment(preferencesUtils)
            ShowAnimalFragment::class.java.name -> ShowAnimalFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}