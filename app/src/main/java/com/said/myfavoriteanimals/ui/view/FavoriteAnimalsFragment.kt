package com.said.myfavoriteanimals.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.databinding.FragmentFavoriteAnimalsBinding
import com.said.myfavoriteanimals.ui.adapter.FavoriteAnimalsAdapter
import com.said.myfavoriteanimals.ui.viewmodel.AnimalListViewModel

class FavoriteAnimalsFragment : Fragment(R.layout.fragment_favorite_animals) {

    private var fragmentBinding: FragmentFavoriteAnimalsBinding? = null
    private lateinit var viewModel: AnimalListViewModel
    private var animalListAdapter: FavoriteAnimalsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetups(view)
        subscribeObservers()
        viewModel.getDataFromSQLite()
    }

    private fun initialSetups(view: View) {
        fragmentBinding = FragmentFavoriteAnimalsBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[AnimalListViewModel::class.java]

        animalListAdapter = FavoriteAnimalsAdapter(arrayListOf())

        fragmentBinding?.let {
            it.recyclerView.adapter = animalListAdapter
            it.recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun subscribeObservers() {
        viewModel.animalsLiveData.observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                fragmentBinding?.let {
                    it.recyclerView.visibility = View.VISIBLE
                    it.tvEmpty.visibility = View.GONE
                }
                animalListAdapter?.updateAnimalList(list)
            } else {
                fragmentBinding?.let {
                    it.recyclerView.visibility = View.GONE
                    it.tvEmpty.visibility = View.VISIBLE
                }
            }
        }
    }
}