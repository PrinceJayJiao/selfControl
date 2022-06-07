package com.jay.selfControl

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.jay.selfControl.databinding.ActivityEditItemBinding
import com.jay.selfControl.pojo.MyActivity
import com.jay.selfControl.utils.DataUtil

class EditItemActivity : AppCompatActivity() {
    lateinit var binding:ActivityEditItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val index = intent.getIntExtra("index",0)

        val dat = intent.getStringExtra("data")
        var array:List<MyActivity>?
        var list:ArrayList<MyActivity>?
        if (!dat.equals("[]")) {
            array = JSON.parseArray(dat, MyActivity::class.java)
            list = ArrayList(array)
        }else list = ArrayList()
        val size = list.size
        if (size<index){
            Toast.makeText(this,"somthing error,please back and try again",Toast.LENGTH_SHORT).show()
        }else{
            initView(list,index)
        }


    }

    private fun initView(list: java.util.ArrayList<MyActivity>, index: Int) {
        val item = list[index]
        binding.name.hint = item.name
        binding.gain.hint = item.gain.toString()
        binding.loss.hint = item.loss.toString()
        binding.time.hint = item.time.toString()

        binding.save.setOnClickListener {
            val name = binding.name.text.toString()
            val gain = binding.gain.text.toString()
            val loss = binding.loss.text.toString()
            val time = binding.time.text.toString()
            if (!name.equals("")) item.name = name
            if (!gain.equals("")) item.gain = Integer.valueOf(gain)
            if (!loss.equals("")) item.loss = Integer.valueOf(loss)
            if (!time.equals("")) item.time = Integer.valueOf(time)
            if (item.gain>0) item.total = item.gain *item.time
            else item.total = item.loss * item.time
            DataUtil.saveData(list,this)
            finish()
        }
        binding.delete.setOnClickListener {
            list.removeAt(index)
            DataUtil.saveData(list,this)
            finish()
        }
    }
}