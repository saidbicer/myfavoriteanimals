package com.said.myfavoriteanimals.ui.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.said.myfavoriteanimals.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject


@MediumTest
@HiltAndroidTest
class AnimalFragmentTest {

    @get: Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: AnimalFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFrom() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<AnimalFragment>(fragmentFactory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(com.said.myfavoriteanimals.R.id.btnListImages)).perform(
            ViewActions.click()
        )

        Mockito.verify(navController).navigate(
            AnimalFragmentDirections.actionAnimalFragmentToAnimalListFragment()
        )
    }

}