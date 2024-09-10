package com.lemu.pay.checkout.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject constructor(
    @ApplicationContext context: Context
) {

   private val pref:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)


    fun clear(){
        val edit = pref.edit()

        for((key) in pref.all){
            edit.remove(key)
        }
        edit.apply()
    }


    fun putString(key:String, value:String){
        val edit = pref.edit()
        edit.putString(key,value)
        edit.apply()
    }

    fun putInt(key:String, value:Int){
        val edit = pref.edit()
        edit.putInt(key,value)
        edit.apply()
    }

    fun getString(key:String,default:String):String?{
        return  pref.getString(key,default)
    }

    fun putToken(token:String){
        putString(TOKEN_KEY,token)
    }

    fun putLastLoginDate(date:String){
        putString(DATE_KEY,date)
    }

    val token:String?
        get() = getString(TOKEN_KEY,"")

    val lastLoginDate:String?
        get() = getString(DATE_KEY,"NO-DATE")


    companion object{
        val TOKEN_KEY:String = "TOKEN_KEY"
        val DATE_KEY:String = "DATE_KEY"
        val username:String = "USERNAME"
    }


}