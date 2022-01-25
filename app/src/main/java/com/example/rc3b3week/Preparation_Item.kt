package com.example.rc3b3week

class Preparation_Item(i:Int, t: String?, b: Boolean) {
    var icon: Int = R.drawable.user_icon
    var checked = false
    var itemString: String? = null

    fun Preparation_Item(i:Int?, t: String?, b: Boolean) {
        icon = i!!
        checked = b
        itemString = t
    }

    fun isChecked(): Boolean {
        return checked
    }
}