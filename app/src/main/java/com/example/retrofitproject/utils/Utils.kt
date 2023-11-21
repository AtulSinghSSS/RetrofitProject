package com.example.retrofitproject.utils

import android.content.Context
import android.provider.Settings

class Utils {
    companion object {
        fun getDeviceUniqueID(context: Context): String? {
            return Settings.Secure.getString(context!!.contentResolver, Settings.Secure.ANDROID_ID)
        }


        fun isNullOrEmpty(s: String?): Boolean {
            return s == null || s.length == 0 || s.equals(
                "null",
                ignoreCase = true
            ) || s.equals("0", ignoreCase = true)
        }
    }


}