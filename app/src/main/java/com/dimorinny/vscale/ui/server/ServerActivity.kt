package com.dimorinny.vscale.ui.server

import android.support.v4.app.Fragment
import com.dimorinny.vscale.ui.OneFragmentActivity

/**
 * Created by Dimorinny on 12.01.16.
 */
class ServerActivity : OneFragmentActivity() {

    override fun createFragment(): Fragment {
        return ServerFragment()
    }
}