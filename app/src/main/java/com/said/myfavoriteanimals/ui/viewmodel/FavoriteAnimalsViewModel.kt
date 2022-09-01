package com.said.myfavoriteanimals.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.data.model.AnimalModel
import com.said.myfavoriteanimals.data.repository.AnimalRepositoryInterface
import com.said.myfavoriteanimals.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteAnimalsViewModel @Inject constructor(private val repository: AnimalRepositoryInterface) : ViewModel() {

    private var _animalsLiveData = MutableLiveData<List<Animal>>()
    val animalsLiveData: LiveData<List<Animal>>
        get() = _animalsLiveData

    fun getDataFromSQLite() {
        viewModelScope.launch {
            _animalsLiveData.value = repository.getAnimalsFromSQLite()
        }
    }

}