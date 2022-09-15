package com.said.myfavoriteanimals.ui.viewmodel

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
class AnimalViewModel @Inject constructor(private val repository: AnimalRepositoryInterface) :
    ViewModel() {

    private var _image = MutableLiveData<Resource<AnimalModel>>()
    val image: LiveData<Resource<AnimalModel>>
        get() = _image

    private val _isSavedInSQLite = MutableLiveData<Boolean>()
    val isSaved: LiveData<Boolean>
        get() = _isSavedInSQLite


    fun getDataFromAPI() {
        _image.value = Resource.loading(null)

        viewModelScope.launch {
            var resource = repository.getImageFromAPI()

            if (resource.status == Status.SUCCESS) {
                resource.data?.let { data ->
                    if (data.status.equals("success")) {
                        data.imgUrl?.let { imgUrl ->
                            _isSavedInSQLite.value = getAnimalWithUrlFromSQLite(imgUrl) != null
                        }
                    } else {
                        resource = Resource.error("Status : ${data.status}", null)
                    }
                }
            }

            _image.value = resource
        }
    }

    private suspend fun getAnimalWithUrlFromSQLite(imgUrl: String): Animal? {
        return repository.getAnimalWithUrlFromSQLite(imgUrl)
    }

    fun saveDataToSqlite() {
        _image.value?.data?.imgUrl?.let { imgUrl ->
            val animal = Animal(null, imgUrl)
            viewModelScope.launch {
                if (getAnimalWithUrlFromSQLite(imgUrl) == null) {
                    repository.insertAnimalToSQLite(animal)
                    _isSavedInSQLite.value = true
                }
            }
        }
    }

}