package com.said.myfavoriteanimals.data.db.dao


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.said.myfavoriteanimals.data.db.MyDatabase
import com.said.myfavoriteanimals.data.db.entity.Animal
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class AnimalDaoHiltTest {

    @get : Rule
    var instantTaskExecutorRole = InstantTaskExecutorRule()

    @get : Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var dao: AnimalDao

    @Inject
    @Named("testDatabase")
    lateinit var database: MyDatabase

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.animalDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAnimalTest() = runBlocking {
        val animal = Animal(1, "https://images.dog.ceo/breeds/samoyed/n02111889_6955.jpg")
        dao.insertAnimal(animal)

        val list = dao.getAllAnimals()
        assertThat(list).contains(animal)
    }

}