package com.dimorinny.vscale.util

import com.dimorinny.vscale.R

/**
 * Created by Dimorinny on 09.01.16.
 */
object ImageUtils {

    private val images = mapOf(
            Pair("ubuntu", R.drawable.ubuntu),
            Pair("centos", R.drawable.centos),
            Pair("debian", R.drawable.ubuntu)
    )

    fun imageByMadeFrom(madeFrom: String?): Int {
        if (madeFrom != null) {
            val prefix = madeFrom.substringBefore('_').toLowerCase()

            if (images.containsKey(prefix)) {
                val image = images[prefix]

                if (image != null) {
                    return image
                }
            }
        }

        return R.drawable.centos
    }
}