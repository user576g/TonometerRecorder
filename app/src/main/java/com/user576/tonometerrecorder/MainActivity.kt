package com.user576.tonometerrecorder

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import com.user576.tonometerrecorder.databinding.MainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding : MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.navView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar, R.string.open_drawer, R.string.close_drawer
        )
        toggle.drawerArrowDrawable.color = getColor(R.color.toolbarForegroundColor)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment : Fragment = when(item.itemId) {
            R.id.records_item -> RecordsFragment()
            else -> TonometerFragment()
        }
        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment, fragment)
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
}