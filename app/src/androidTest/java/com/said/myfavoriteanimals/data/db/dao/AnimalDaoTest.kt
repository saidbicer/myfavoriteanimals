package com.said.myfavoriteanimals.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.said.myfavoriteanimals.data.db.MyDatabase
import com.said.myfavoriteanimals.data.db.entity.Animal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
@ExperimentalCoroutinesApi
class AnimalDaoTest {

    @get : Rule
    var instantTastExecutorRole = InstantTaskExecutorRule()

    private lateinit var dao: AnimalDao
    private lateinit var database: MyDatabase

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), MyDatabase::class.java).allowMainThreadQueries().build()
        dao = database.animalDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertAnimalTest() = runBlocking {
        val animal = Animal(1,"https://images.dog.ceo/breeds/samoyed/n02111889_6955.jpg")
        dao.insertAnimal(animal)

        val list = dao.getAllAnimals()
        assertThat(list).contains(animal)
    }

}