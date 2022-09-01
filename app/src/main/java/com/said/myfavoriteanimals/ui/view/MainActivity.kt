package com.said.myfavoriteanimals.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.said.myfavoriteanimals.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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