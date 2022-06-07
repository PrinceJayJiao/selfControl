package com.jay.selfControl.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.fastjson.JSON
import com.jay.selfControl.EditItemActivity
import com.jay.selfControl.R
import com.jay.selfControl.pojo.MyActivity

class ActivityAdapter(val list:ArrayList<MyActivity>,val context: Context):
    RecyclerView.Adapter<ActivityAdapter.viewHolder>() {
    class viewHolder(view:View):RecyclerView.ViewHolder(view){
        val gain =view.findViewById<TextView>(R.id.total)
        val activity = view.findViewById<TextView>(R.id.activity)
        val item = view.findViewById<RelativeLayout>(R.id.itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val myActivity = list[position]
        holder.activity.text = myActivity.name
        holder.gain.text = myActivity.total.toString()
        if (myActivity.total> 0){
            holder.gain.setTextColor(Color.GREEN)
        }else{
            holder.activity.setTextColor(Color.RED)
            holder.gain.setTextColor(Color.RED)
        }
        holder.item.setOnClickListener {
            val intent = Intent(context,EditItemActivity::class.java)
            val str = JSON.toJSONString(list)
            intent.putExtra("data",str)
            intent.putExtra("index",position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}