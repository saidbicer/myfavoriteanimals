package com.said.myfavoriteanimals.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.databinding.FragmentAnimalBinding
import com.said.myfavoriteanimals.ui.viewmodel.AnimalViewModel


class AnimalFragment : Fragment(R.layout.fragment_animal) {

    private var fragmentBinding: FragmentAnimalBinding? = null
    private lateinit var viewModel: AnimalViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetups(view)
    }

    private fun initialSetups(view : View) {
        fragmentBinding = FragmentAnimalBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[AnimalViewModel::class.java]

        fragmentBinding?.let { binding ->
            binding.apply {
                btnGetRandomImage.setOnClickListener {

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

}