package com.beyzanuraydemir.ecommerceapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PrivateSharedPreferences {

    companion object{
        private val TIME = "time"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance : PrivateSharedPreferences? = null

        private val lock = Any()
        operator fun invoke(context: Context) : PrivateSharedPreferences = instance ?: synchronized(lock){
            instance ?: createPrivateSharedPreferences(context).also {
                instance = it
            }
        }

        private fun createPrivateSharedPreferences(context: Context) : PrivateSharedPreferences{
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return PrivateSharedPreferences()
        }
    }

    fun saveTime(time: Long){
        sharedPreferences?.edit(commit = true){
            putLong(TIME, time)
        }
    }

    fun takeTheTime() = sharedPreferences?.getLong(TIME,0)


}