package com.dimorinny.vscale.ui.server

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.dimorinny.vscale.R
import com.dimorinny.vscale.ui.OneFragmentActivity
import com.r0adkll.slidr.Slidr

/**
 * Created by Dimorinny on 12.01.16.
 */
class ServerActivity : OneFragmentActivity() {

    override fun createFragment(): Fragment {
        return ServerFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Slidr.attach(this);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.activity_out)
    }
}