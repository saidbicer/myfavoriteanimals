package com.said.myfavoriteanimals.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.said.myfavoriteanimals.data.db.entity.Animal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowAnimalViewModel @Inject constructor() : ViewModel() {

    private val imgUrl_ = MutableLiveData<String?>()
    val imgUrl: LiveData<String?>
        get() = imgUrl_

    fun changeImgUrl(imgUrl: Animal?){
        imgUrl_.value = imgUrl?.imgUrl
    }
}