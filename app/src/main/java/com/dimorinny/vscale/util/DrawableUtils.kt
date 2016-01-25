package com.dimorinny.vscale.util

import android.content.Context
import android.graphics.drawable.Drawable

/**
 * Created by Dimorinny on 25.01.16.
 */
object DrawableUtils {
    fun getDrawable(context: Context, resourceId: Int): Drawable {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            context.getDrawable(resourceId)
        } else {
            context.resources.getDrawable(resourceId)
        }
    }
}