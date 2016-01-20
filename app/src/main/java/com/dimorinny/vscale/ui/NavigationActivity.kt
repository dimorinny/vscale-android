package com.dimorinny.vscale.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.dimorinny.vscale.R
import com.dimorinny.vscale.dependency.bindView
import com.dimorinny.vscale.ui.servers.ServersFragment

/**
 * Created by Dimorinny on 12.01.16.
 */
class NavigationActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    val drawerLayout: DrawerLayout by bindView(R.id.drawer)
    val navigationView: NavigationView by bindView(R.id.navigation)
    val toolbar: Toolbar by bindView(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        initToolbar()
        initViews()

        if (savedInstanceState == null) {
            setDefaultContent()
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setDefaultContent() {
        setFragment(ServersFragment())
        navigationView.setCheckedItem(R.id.nav_servers)
    }

    private fun initViews() {
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.nav_servers -> setFragment(ServersFragment())
            R.id.nav_tickets, R.id.nav_settings -> RuntimeException("Don't click here")
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment)
            .commitAllowingStateLoss()
    }
}