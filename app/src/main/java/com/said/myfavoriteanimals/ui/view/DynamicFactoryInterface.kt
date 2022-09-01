package com.said.myfavoriteanimals.ui.view

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DynamicFactoryInterface {
    fun getFactory() : AnimalFragmentFactory
}
