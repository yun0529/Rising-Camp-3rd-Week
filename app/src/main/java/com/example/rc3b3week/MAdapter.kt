package com.example.rc3b3week

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.rc3b3week.databinding.ItemPhoneBinding
import android.content.SharedPreferences

private lateinit var binding: ItemPhoneBinding

class MAdapter(val context: Context, val personList: ArrayList<Person>) : BaseAdapter() {

    //바인딩에 들어갈 인플레이트를 만들어 줘야함
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    // list 내용물의 갯수
    override fun getCount(): Int = personList.size

    // list에 있는 p0위치의 값을 가져옴
    override fun getItem(p0: Int): Any =personList[p0]

    //p0 는 position 즉, 위치라고 생각하면됨
    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        binding = ItemPhoneBinding.inflate(inflater, p2, false)

        binding.ivProfileImage.setImageResource(personList[p0].profileImage)
        binding.tvUserName.text = personList[p0].name
        binding.tvUserCount.text = personList[p0].count
        binding.tvChat.text = personList[p0].chat
        binding.tvTime.text = personList[p0].time

        return binding.root
    }

}