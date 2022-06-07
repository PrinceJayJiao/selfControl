package com.jay.selfControl.pojo

class MyActivity(var name:String,
                 var gain:Int,
                 var loss:Int,
                 var time:Int,
                 var total:Int) {
    constructor():this("",0,0,0,0)
}