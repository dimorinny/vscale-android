package com.dimorinny.vscale.util

import android.content.Intent
import android.os.Bundle

/**
 * Created by Dimorinny on 12.01.16.
 */
object IntentUtils {

    fun intentToFragmentArguments(arguments: Bundle?, intent: Intent?): Bundle {

        var fragmentArguments = arguments

        if (fragmentArguments == null) {
            fragmentArguments = Bundle() // создаем новый объект типа Bundle
        }

        if (intent == null) {
            return fragmentArguments
        }

        if (intent.data != null) {
            fragmentArguments.putParcelable("_uri", intent.data)
        }

        if (intent.extras != null) {
            fragmentArguments.putAll(intent.extras)
        }

        return fragmentArguments
    }
}