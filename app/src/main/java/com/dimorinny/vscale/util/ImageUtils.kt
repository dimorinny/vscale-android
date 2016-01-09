package com.dimorinny.vscale.util

/**
 * Created by Dimorinny on 09.01.16.
 */
object ImageUtils {

    private val images = mapOf(
            Pair("ubuntu", 1),
            Pair("centos", 2),
            Pair("debian", 3)
    )

    fun imageByMadeFrom(madeFrom: String): Int? {
        val prefix = madeFrom.substringBefore('_')

        if (images.containsKey(prefix)) {
            return images[prefix]
        }

        return 0
    }
}