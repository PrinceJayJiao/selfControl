package com.jay.selfControl

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.fastjson.JSON
import com.jay.selfControl.adapter.ActivityAdapter
import com.jay.selfControl.databinding.ActivityMainBinding
import com.jay.selfControl.utils.DataUtil
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this,"oncreate",Toast.LENGTH_SHORT)
        initView()


    }

    override fun onRestart() {
        super.onRestart()
        initView()
    }
    private fun initView() {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val str = sdf.format(Date())
        val list = DataUtil.loadData(str,this)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager
        val adapter = ActivityAdapter(list,this)
        binding.recyclerview.adapter = adapter
        var total = 0
        for (item in list){
            total += item.total
        }
        binding.total.text = total.toString()
        if (total>0) binding.total.setTextColor(Color.GREEN)
        binding.fab.setOnClickListener {
            val intent = Intent(this,AddItemActivity::class.java)
            val str = JSON.toJSONString(list)
            intent.putExtra("data",str)
            startActivity(intent)
        }
    }
}