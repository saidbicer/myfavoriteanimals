package com.said.myfavoriteanimals.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.said.myfavoriteanimals.data.api.RetrofitAPI
import com.said.myfavoriteanimals.data.db.MyDatabase
import com.said.myfavoriteanimals.data.db.dao.AnimalDao
import com.said.myfavoriteanimals.data.repository.AnimalRepository
import com.said.myfavoriteanimals.data.repository.AnimalRepositoryInterface
import com.said.myfavoriteanimals.ui.adapter.FavoriteAnimalsAdapter
import com.said.myfavoriteanimals.util.ConnectionChecker
import com.said.myfavoriteanimals.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, MyDatabase::class.java, "AnimalDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database: MyDatabase) = database.animalDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI(): RetrofitAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(dao: AnimalDao, api: RetrofitAPI, connectionChecker: ConnectionChecker) =
        AnimalRepository(dao, api, connectionChecker) as AnimalRepositoryInterface

    @Singleton
    @Provides
    fun injectSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.packageName + "_preferences",
            Context.MODE_PRIVATE
        )
    }

    @Provides
    fun injectAdapter() = FavoriteAnimalsAdapter(arrayListOf())
}