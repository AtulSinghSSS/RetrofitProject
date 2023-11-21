package com.example.retrofitproject

import android.content.Context
import com.example.retrofitproject.sharePrefrence.SharePrefs


class SavePeopleLocalInfo {
    companion object {
        fun  savePeopleInfo(applicationContext: Context, it: PeopleModel,isSplash:Boolean){
            SharePrefs.getInstance(applicationContext)


          }

//        fun  saveCheckInInfo(applicationContext: Context, isPhoneOrder: Boolean,isPeopleEnterCatalog:Boolean){
//            SharePrefs.getInstance(applicationContext).putBoolean(SharePrefs.IS_PHONE_ORDER, isPhoneOrder)
//            SharePrefs.getInstance(applicationContext).putBoolean(SharePrefs.IS_PEOPLE_ENTER_CATALOG, isPeopleEnterCatalog)
//
//        }
    }
}