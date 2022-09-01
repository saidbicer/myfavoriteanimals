package com.said.myfavoriteanimals.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.databinding.FragmentFavoriteAnimalsBinding
import com.said.myfavoriteanimals.ui.viewmodel.AnimalListViewModel

class FavoriteAnimalsFragment : Fragment(R.layout.fragment_favorite_animals) {

    private var fragmentBinding: FragmentFavoriteAnimalsBinding? = null
    private lateinit var viewModel: AnimalListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetups(view)
    }

    private fun initialSetups(view: View) {
        fragmentBinding = FragmentFavoriteAnimalsBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[AnimalListViewModel::class.java]

    }

}