package com.said.myfavoriteanimals.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.databinding.FragmentAnimalBinding
import com.said.myfavoriteanimals.ui.viewmodel.AnimalViewModel
import com.said.myfavoriteanimals.util.Status
import com.said.myfavoriteanimals.util.downloadFromUrl


class AnimalFragment : Fragment(R.layout.fragment_animal) {

    private var fragmentBinding: FragmentAnimalBinding? = null
    private lateinit var viewModel: AnimalViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetups(view)
        subscribeObservers()
    }

    private fun initialSetups(view : View) {
        fragmentBinding = FragmentAnimalBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[AnimalViewModel::class.java]

        fragmentBinding?.let { binding ->
            binding.apply {
                btnGetRandomImage.setOnClickListener {
                    viewModel.getDataFromAPI()
                }

                btnSaveImage.setOnClickListener {

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

                        resource.data?.imgUrl?.let { imgUrl ->
                            bidding.ivAnimal.downloadFromUrl(imgUrl)
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
    }
}