package com.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.finalproject.adapter.UserAdapter
import com.finalproject.data.api.response.UserItem
import com.finalproject.databinding.ActivityMainBinding
import com.finalproject.fragments.FavoriteFragment
import com.finalproject.fragments.HomeFragment
import com.finalproject.fragments.ProfileFragment
import com.finalproject.utils.createSearchViewMenu
import com.finalproject.utils.gone
import com.finalproject.utils.visible
import com.finalproject.viewmodel.GithubUserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: HomeFragment
    private lateinit var favoriteFragment: FavoriteFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Final Github User"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val btmNav : BottomNavigationView = binding.btmNav

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerFragment, homeFragment, "HOME_FRAGMENT")
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()

        btmNav.setOnNavigationItemSelectedListener { item ->

            when (item.itemId){

                R.id.home -> {

                    homeFragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.containerFragment, homeFragment, "HOME_FRAGMENT")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit()
                }

                R.id.favorite -> {

                    favoriteFragment = FavoriteFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.containerFragment, favoriteFragment, "FAVORITE_FRAGMENT")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit()
                }

                R.id.profile -> {

                    profileFragment = ProfileFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.containerFragment, profileFragment, "PROFILE_FRAGMENT")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit()
                }
            }
            true
        }
    }
}