package com.said.myfavoriteanimals.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.databinding.FragmentAnimalListBinding
import com.said.myfavoriteanimals.ui.viewmodel.AnimalListViewModel

class AnimalListFragment : Fragment(R.layout.fragment_animal_list) {

    private var fragmentBinding: FragmentAnimalListBinding? = null
    private lateinit var viewModel: AnimalListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetups(view)
    }

    private fun initialSetups(view: View) {
        fragmentBinding = FragmentAnimalListBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[AnimalListViewModel::class.java]

    }

}