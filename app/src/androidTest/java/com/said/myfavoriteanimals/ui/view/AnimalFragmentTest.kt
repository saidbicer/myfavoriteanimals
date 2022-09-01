package com.said.myfavoriteanimals.ui.view

import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@MediumTest
@HiltAndroidTest
class AnimalFragmentTest {

    @get: Rule
    val hiltRule = HiltAndroidRule(this)

    @get: Rule
    lateinit var fragmentFactory: AnimalFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testNavigationFrom() {
        
    }

}