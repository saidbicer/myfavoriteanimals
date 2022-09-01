package com.said.myfavoriteanimals.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.said.myfavoriteanimals.data.repository.FakeAnimalRepository
import com.said.myfavoriteanimals.util.MainCoroutineRule
import com.said.myfavoriteanimals.util.Status
import com.said.myfavoriteanimals.util.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AnimalViewModelTest {

    @get:Rule
    var instantTaskExecuteRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: AnimalViewModel
    private lateinit var fakeAnimalRepository: FakeAnimalRepository

    @Before
    fun setup() {
        fakeAnimalRepository = FakeAnimalRepository()
        viewModel = AnimalViewModel(fakeAnimalRepository)
    }

    @Test
    fun `Api üzerinden gelen status alanı 'success' ise testi geçmeli`() {
        fakeAnimalRepository.setAnimalModel(
            "https://images.dog.ceo/breeds/samoyed/n02111889_6955.jpg",
            "success"
        )
        viewModel.getDataFromAPI()
        val value = viewModel.image.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `Api üzerinden gelen status 'success' değilse error dönecek`() {
        fakeAnimalRepository.setAnimalModel(
            "https://images.dog.ceo/breeds/samoyed/n02111889_6955.jpg",
            "-----"
        )
        viewModel.getDataFromAPI()
        val value = viewModel.image.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}