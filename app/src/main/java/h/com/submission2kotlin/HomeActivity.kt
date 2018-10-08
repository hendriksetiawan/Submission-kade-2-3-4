package h.com.submission2kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import h.com.submission2kotlin.R.id.*
import h.com.submission2kotlin.fragment.FavoriteMatchFragment
import h.com.submission2kotlin.fragment.NextMatchFragment
import h.com.submission2kotlin.fragment.PrevMatchFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom__navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                prev_match ->{
                    loadPrevMatchFragment(savedInstanceState)
                }
                next_match->{
                    loadNextMatchFragment(savedInstanceState)
                }
                favorites->{
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom__navigation.selectedItemId = prev_match
    }
    private fun loadPrevMatchFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, PrevMatchFragment(), PrevMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadFavoritesFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                    .commit()
        }
    }
}
