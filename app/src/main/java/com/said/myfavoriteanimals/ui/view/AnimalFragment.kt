package com.said.myfavoriteanimals.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
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
import javax.inject.Inject

@AndroidEntryPoint
class AnimalFragment @Inject constructor(private val preferencesUtils: PreferencesUtils) :
    Fragment(R.layout.fragment_animal) {

    private var fragmentBinding: FragmentAnimalBinding? = null
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

        fragmentBinding?.let { binding ->
            binding.apply {
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
    }

    private fun subscribeObservers() {
        viewModel.image.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    fragmentBinding?.let { bidding ->
                        bidding.ivAnimal.visibility = View.VISIBLE
                        bidding.progressBar.visibility = View.GONE
                        bidding.btnGetRandomImage.isEnabled = true
                        bidding.btnSaveImage.isEnabled = true

                        if (resource.data?.status.equals("success")) {
                            resource.data?.imgUrl?.let { imgUrl ->
                                bidding.animal = Animal(null, imgUrl)
                                preferencesUtils.setLastTakenImgUrl(imgUrl)
                            }
                        } else {
                            bidding.animal = Animal(null, "")
                            preferencesUtils.clearLastImgUrl()
                        }

                    }
                }

                Status.ERROR -> {
                    fragmentBinding?.let { bidding ->
                        bidding.ivAnimal.visibility = View.VISIBLE
                        bidding.ivAnimal.setImageResource(R.drawable.ic_launcher_background)
                        bidding.btnGetRandomImage.isEnabled = true
                        bidding.progressBar.visibility = View.GONE
                    }
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG)
                        .show()
                }

                Status.LOADING -> {
                    fragmentBinding?.let { bidding ->
                        bidding.ivAnimal.visibility = View.INVISIBLE
                        bidding.btnGetRandomImage.isEnabled = false
                        bidding.btnSaveImage.isEnabled = false
                        bidding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }

        viewModel.isSaved.observe(viewLifecycleOwner) { isSaved ->
            fragmentBinding?.let { root ->
                root.btnSaveImage.isEnabled = !isSaved
            }
        }
    }
}