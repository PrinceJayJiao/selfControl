package com.jay.selfControl

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.jay.selfControl.databinding.ActivityAddItemBinding
import com.jay.selfControl.pojo.MyActivity
import com.jay.selfControl.utils.DataUtil

class AddItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dat = intent.getStringExtra("data")
        var array:List<MyActivity>?
        var list:ArrayList<MyActivity>?
        if (!dat.equals("[]")) {
            array = JSON.parseArray(dat,MyActivity::class.java)
            list = ArrayList(array)
        }else list = ArrayList()

        binding.button.setOnClickListener {
            val name = binding.name.text.toString()
            val gain = binding.gain.text.toString()
            val loss = binding.loss.text.toString()
            val time = binding.time.text.toString()
            if (name.equals("")||time.equals("")||(gain.equals("")&&loss.equals(""))){
                Toast.makeText(this,"请按照要求输入",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var total = 0
            var item:MyActivity?
            if (gain.equals("")){
                total = Integer.valueOf(loss) * Integer.valueOf(time)
                item = MyActivity(name,0,Integer.valueOf(loss),Integer.valueOf(time),total)
            }else{
                total = Integer.valueOf(gain) * Integer.valueOf(time)
                item = MyActivity(name,Integer.valueOf(gain),0,Integer.valueOf(time),total)
            }

            list.add(item)
            DataUtil.saveData(list,this)
            finish()
        }

    }
}