package com.said.myfavoriteanimals.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.databinding.FragmentShowAnimalBinding
import com.said.myfavoriteanimals.ui.viewmodel.ShowAnimalViewModel
import com.said.myfavoriteanimals.util.downloadFromUrl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowAnimalFragment @Inject constructor() : Fragment(R.layout.fragment_show_animal) {


    private lateinit var binding: FragmentShowAnimalBinding
    private val viewModel : ShowAnimalViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup(view)
        subscribleObserves()
    }

    private fun setup(view: View) {
        binding = FragmentShowAnimalBinding.bind(view)

        arguments?.let {
            val imgUrl = it.getString("imgUrl")
            viewModel.changeImgUrl(it.getParcelable<Animal>("animal"))
        }

    }

    private fun subscribleObserves() {
        viewModel.imgUrl.observe(viewLifecycleOwner) {
            binding.ivAnimal.downloadFromUrl(it)
        }
    }

}