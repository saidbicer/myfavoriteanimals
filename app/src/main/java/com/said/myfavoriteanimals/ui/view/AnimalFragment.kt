package com.said.myfavoriteanimals.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.databinding.FragmentAnimalBinding


class AnimalFragment : Fragment(R.layout.fragment_animal) {

    private var fragmentBinding: FragmentAnimalBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetups(view)

    }

    private fun initialSetups(view : View) {
        fragmentBinding = FragmentAnimalBinding.bind(view)

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