package com.example.mittechapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mittechapp.CommonUtils.CommonUtils
import com.example.mittechapp.R
import com.example.mittechapp.View.fragment.*
import com.example.mittechapp.databinding.ActivityNavigationBinding
import com.example.mittechapp.databinding.CommonHedderBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity() {

    lateinit var bottomnavview: BottomNavigationView
    lateinit var binding: ActivityNavigationBinding
    lateinit var commonheaderbinding: CommonHedderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_navigation)
        supportActionBar!!.hide()
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        commonheaderbinding = CommonHedderBinding.bind(binding.root)
        setContentView(binding.root)

        onClickOnUi()
    }

    private fun onClickOnUi() {

        commonheaderbinding.ibBack.setOnClickListener {
            this.startActivity(
                Intent(this, MainActivity::class.java).apply {
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
                    Intent.FLAG_ACTIVITY_NEW_TASK
                    Intent.FLAG_ACTIVITY_NO_HISTORY
                }
            )
        }
        commonheaderbinding.ibHome.setOnClickListener {
            CommonUtils.snakeBarPopUp(binding.linnavigation, "Notification Empty")
        }
        commonheaderbinding.tvHeaderText.text = "Veg"


        var Informantion = Information()
        var Home = HomeFragment()
        var Details = DetailsFragment()
        var Setting = SettingFragment()
        var checking = FragmentChecking()

        setFragment(Informantion)
        bottomnavview = binding.topNavigation

        bottomnavview.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.info -> {
                    setFragment(Informantion)
                }
                R.id.home -> {
                    setFragment(Home)
                }
                R.id.details -> {
                    setFragment(Details)
                }
                R.id.setting -> {
                    setFragment(Setting)
                }
                R.id.checking -> {
                    setFragment(checking)
                }
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }

}