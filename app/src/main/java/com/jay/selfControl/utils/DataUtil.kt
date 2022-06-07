package com.jay.selfControl.utils

import android.content.Context
import com.alibaba.fastjson.JSON
import com.jay.selfControl.pojo.MyActivity
import java.text.SimpleDateFormat
import java.util.*

class DataUtil {
    companion object{
        fun saveData(list: ArrayList<MyActivity>,context: Context){

            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val dateStr = sdf.format(Date())
            val key = "record$dateStr"
            val json = JSON.toJSONString(list)
            val editor = context.getSharedPreferences("data",Context.MODE_PRIVATE).edit()
            editor.putString(key,json)
            editor.apply()


        }

        fun loadData(str: String,context:Context): ArrayList<MyActivity> {
            val key = "record$str"
            val pref = context.getSharedPreferences("data",Context.MODE_PRIVATE)
            val value = pref.getString(key,"")
            val res = JSON.parseArray(value,MyActivity::class.java) ?: return ArrayList()
            return  ArrayList(res)
        }


    }
}