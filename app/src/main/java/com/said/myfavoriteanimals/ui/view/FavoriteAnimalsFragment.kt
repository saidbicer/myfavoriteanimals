package com.said.myfavoriteanimals.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.databinding.FragmentFavoriteAnimalsBinding
import com.said.myfavoriteanimals.ui.adapter.FavoriteAnimalsAdapter
import com.said.myfavoriteanimals.ui.viewmodel.FavoriteAnimalsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteAnimalsFragment @Inject constructor(private val animalListAdapter: FavoriteAnimalsAdapter) : Fragment(R.layout.fragment_favorite_animals) {

    private lateinit var fragmentBinding: FragmentFavoriteAnimalsBinding
    private val viewModel: FavoriteAnimalsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetups(view)
        subscribeObservers()
        viewModel.getDataFromSQLite()
    }

    private fun initialSetups(view: View) {
        fragmentBinding = FragmentFavoriteAnimalsBinding.bind(view)

        fragmentBinding.apply {
            recyclerView.adapter = animalListAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }

    }

    private fun subscribeObservers() {
        viewModel.animalsLiveData.observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                fragmentBinding.apply {
                    recyclerView.visibility = View.VISIBLE
                    tvEmpty.visibility = View.GONE
                }
                animalListAdapter.updateAnimalList(list)
            } else {
                fragmentBinding.apply {
                    recyclerView.visibility = View.GONE
                    tvEmpty.visibility = View.VISIBLE
                }
            }
        }
    }
}