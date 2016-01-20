package com.dimorinny.vscale.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import com.dimorinny.vscale.R
import com.dimorinny.vscale.dependency.bindView
import com.dimorinny.vscale.util.IntentUtils

/**
 * Created by Dimorinny on 12.01.16.
 */

abstract class OneFragmentActivity : BaseActivity() {

    protected var fragment: Fragment? = null

    protected abstract fun createFragment(): Fragment

    protected val layout: Int
        get() = R.layout.activity_one_fragment

    protected val toolbar: Toolbar by bindView(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        initToolbar()

        if (!isFinishing) {
            val fragmentManager = supportFragmentManager

            var fragment: Fragment? = fragmentManager.findFragmentById(R.id.main_container)

            if (fragment == null) {
                fragment = createFragment()

                // Pass args
                fragment.arguments = IntentUtils.intentToFragmentArguments(fragment.arguments, intent)

                // Add fragment
                fragmentManager!!.beginTransaction().replace(R.id.main_container, fragment).commitAllowingStateLoss()
            }

            // Save fragment
            this.fragment = fragment
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true)
        }
    }
}