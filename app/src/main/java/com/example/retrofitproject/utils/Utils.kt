package com.example.retrofitproject.utils

import android.content.Context
import android.provider.Settings

class Utils {
    companion object {
        fun getDeviceUniqueID(context: Context): String? {
            return Settings.Secure.getString(context!!.contentResolver, Settings.Secure.ANDROID_ID)
        }
    }

}