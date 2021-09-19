package com.user576.tonometerrecorder

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import com.user576.tonometerrecorder.databinding.DrawerLayoutBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding : DrawerLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DrawerLayoutBinding.inflate(layoutInflater)
        binding.navView.setNavigationItemSelectedListener(this)
        setContentView(binding.root)
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