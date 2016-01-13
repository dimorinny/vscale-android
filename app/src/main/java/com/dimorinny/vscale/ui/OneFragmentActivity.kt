package com.dimorinny.vscale.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import com.dimorinny.vscale.R
import com.dimorinny.vscale.util.IntentUtils

/**
 * Created by Dimorinny on 12.01.16.
 */

abstract class OneFragmentActivity : BaseActivity() {

    protected var fragment: Fragment? = null
        private set

    protected abstract fun createFragment(): Fragment

    protected val layout: Int
        get() = R.layout.activity_one_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

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
}