package com.said.myfavoriteanimals.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.util.PreferencesUtils
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var preferencesUtils: PreferencesUtils

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        val factory = EntryPointAccessors.fromApplication(this, DynamicFactoryInterface::class.java)
        supportFragmentManager.fragmentFactory = factory.getFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferencesUtils.clearLastImgUrl()

        initialSetups()
    }

    private fun initialSetups() {
        navHostFragment =  supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.findNavController())
    }

    override fun onSupportNavigateUp(): Boolean {
        return navHostFragment.findNavController().navigateUp()
    }

}
