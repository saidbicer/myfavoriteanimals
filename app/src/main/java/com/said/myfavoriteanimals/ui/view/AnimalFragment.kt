package com.said.myfavoriteanimals.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.databinding.FragmentAnimalBinding
import com.said.myfavoriteanimals.ui.viewmodel.AnimalViewModel
import com.said.myfavoriteanimals.util.PreferencesUtils
import com.said.myfavoriteanimals.util.Status
import com.said.myfavoriteanimals.util.downloadFromUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class AnimalFragment @Inject constructor(private val preferencesUtils: PreferencesUtils) :
    Fragment(R.layout.fragment_animal) {

    private lateinit var fragmentBinding: FragmentAnimalBinding
    private val viewModel: AnimalViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetups(view)
        subscribeObservers()

        if (preferencesUtils.getLastTakenImgUrl().isNullOrEmpty()) {
            viewModel.getDataFromAPI()
        }
    }

    private fun initialSetups(view: View) {
        fragmentBinding = FragmentAnimalBinding.bind(view)

        fragmentBinding.apply {
            btnGetRandomImage.setOnClickListener {
                viewModel.getDataFromAPI()
            }

            btnSaveImage.setOnClickListener {
                viewModel.saveDataToSqlite()
            }

            btnListImages.setOnClickListener {
                val action = AnimalFragmentDirections.actionAnimalFragmentToAnimalListFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun subscribeObservers() {
        viewModel.image.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    fragmentBinding.apply {
                        ivAnimal.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        btnGetRandomImage.isEnabled = true
                        btnSaveImage.isEnabled = true
                    }

                    resource.data?.imgUrl?.let { imgUrl ->
                        fragmentBinding.animal = Animal(null, imgUrl, "", 0)
                        preferencesUtils.setLastTakenImgUrl(imgUrl)
                    }
                }

                Status.ERROR -> {
                    fragmentBinding.apply {
                        ivAnimal.visibility = View.VISIBLE
                        ivAnimal.setImageResource(R.drawable.ic_launcher_background)
                        btnGetRandomImage.isEnabled = true
                        progressBar.visibility = View.GONE
                    }
                    preferencesUtils.clearLastImgUrl()
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG)
                        .show()
                }

                Status.LOADING -> {
                    fragmentBinding.apply {
                        ivAnimal.visibility = View.INVISIBLE
                        btnGetRandomImage.isEnabled = false
                        btnSaveImage.isEnabled = false
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }

        viewModel.isSaved.observe(viewLifecycleOwner) { isSaved ->
            fragmentBinding.btnSaveImage.isEnabled = !isSaved
        }
    }
}