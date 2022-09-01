package com.said.myfavoriteanimals.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.data.model.AnimalModel
import com.said.myfavoriteanimals.data.repository.AnimalRepositoryInterface
import com.said.myfavoriteanimals.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(application: Application, private val repository: AnimalRepositoryInterface) : BaseViewModel(application) {

    private var _image = MutableLiveData<Resource<AnimalModel>>()
    val image: LiveData<Resource<AnimalModel>>
        get() = _image


    fun getDataFromAPI() {
        _image.value = Resource.loading(null)

        launch {
            val resource = repository.getImageFromAPI()
            _image.value = resource
        }
    }

    fun saveDataToSqlite(){
        _image.value?.data?.imgUrl?.let { imgUrl ->
            val animal = Animal(null, imgUrl)
            launch {
                repository.insertAnimalToSQLite(animal)
            }
        }
    }
}