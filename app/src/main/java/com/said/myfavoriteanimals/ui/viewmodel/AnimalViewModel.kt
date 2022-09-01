package com.said.myfavoriteanimals.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.data.model.AnimalModel
import com.said.myfavoriteanimals.data.repository.AnimalRepositoryInterface
import com.said.myfavoriteanimals.util.Resource
import com.said.myfavoriteanimals.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(private val repository: AnimalRepositoryInterface) : ViewModel() {

    private var _image = MutableLiveData<Resource<AnimalModel>>()
    val image: LiveData<Resource<AnimalModel>>
        get() = _image

    private val _isSaved = MutableLiveData<Boolean>()
    val isSaved: LiveData<Boolean>
        get() = _isSaved

    fun getDataFromAPI() {
        _image.value = Resource.loading(null)

        viewModelScope.launch {
            var resource = repository.getImageFromAPI()
            if (!resource.data?.status.equals("success")){
                resource = Resource.error("No data", null)
            }

            _image.value = resource

            if (resource.status == Status.SUCCESS) {
                resource.data?.imgUrl?.let { imgUrl ->
                    checkSavedStatus(imgUrl)
                }
            }
        }
    }

    private suspend fun checkSavedStatus(imgUrl: String) {
        val animal = repository.getAnimalWithUrlFromSQLite(imgUrl)
        _isSaved.value = animal != null
    }

    fun saveDataToSqlite(){
        _image.value?.data?.imgUrl?.let { imgUrl ->
            val animal = Animal(null, imgUrl)
            viewModelScope.launch {
                repository.insertAnimalToSQLite(animal)
                _isSaved.value = true
            }
        }
    }
}