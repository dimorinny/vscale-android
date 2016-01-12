package com.dimorinny.vscale.util

import com.dimorinny.vscale.R

/**
 * Created by Dimorinny on 12.01.16.
 */
object StatusUtils {
    fun getStatus(status: String?) : Int {
        return when(status) {
            "started" -> R.string.status_started
            "stopped" -> R.string.status_stopped
            "billing" -> R.string.status_billing
            else -> R.string.status_unknown
        }
    }
}