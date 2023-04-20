package com.example.familyapp.data.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.familyapp.MyApplication
import com.example.familyapp.R
import com.example.familyapp.data.preferences.PrefConstants.PREF_EXPIRED_TIME
import com.example.familyapp.data.preferences.PrefConstants.PREF_FAMILY_ID
import com.example.familyapp.data.preferences.PrefConstants.PREF_KEY
import com.example.familyapp.data.preferences.PrefConstants.PREF_LOGGED_IN
import com.example.familyapp.data.preferences.PrefConstants.PREF_TOKEN
import com.example.familyapp.data.preferences.PrefConstants.PREF_USER_ID
import com.example.familyapp.data.preferences.PrefConstants.PREF_USER_NAME
import java.util.*

class Preferences private constructor(){
    private val mPrefs: SharedPreferences
    private val mEdit: SharedPreferences.Editor

    val token: String?
        get() = instance.mPrefs.getString(PREF_TOKEN, "")

    val userID: String?
        get() = instance.mPrefs.getString(PREF_USER_ID, "")

    val familyID: String?
        get() = instance.mPrefs.getString(PREF_FAMILY_ID, "")

    val userName: String?
        get() = instance.mPrefs.getString(PREF_USER_NAME, "")

    val loggedIn: Boolean
        get() = instance.mPrefs.getBoolean(PREF_LOGGED_IN, false)

    val expiredTime: Long
        get() = instance.mPrefs.getLong(PREF_EXPIRED_TIME, 0L)

    fun setToken(value: String) {
        mEdit.putString(PREF_TOKEN, value)
        mEdit.apply()
    }

    fun setUserID(value: String) {
        mEdit.putString(PREF_USER_ID, value)
        mEdit.apply()
    }

    fun setFamilyID(value: String) {
        mEdit.putString(PREF_FAMILY_ID, value)
        mEdit.apply()
    }

    fun setUserName(value: String) {
        mEdit.putString(PREF_USER_NAME, value)
        mEdit.apply()
    }

    fun setExpirationTime(value: Int) {
        val cal = Calendar.getInstance()
        cal.add(Calendar.SECOND, value)
        mEdit.putLong(PREF_EXPIRED_TIME, cal.timeInMillis)
        mEdit.apply()
    }

    fun isLoggedIn(value: Boolean) {
        mEdit.putBoolean(PREF_LOGGED_IN, value)
        mEdit.apply()
    }

    fun clearData(context: Context){
        val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        var INSTANCE: Preferences? = null
        val instance: Preferences
            get() {
                if (INSTANCE == null) INSTANCE = Preferences()
                return INSTANCE as Preferences
            }
    }

    init {
        val app: Application = MyApplication.instance
        mPrefs = app.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        mEdit = mPrefs.edit()
    }
}