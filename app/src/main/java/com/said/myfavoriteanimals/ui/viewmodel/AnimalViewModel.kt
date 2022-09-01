package com.said.myfavoriteanimals.ui.viewmodel

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

}