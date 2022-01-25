package com.example.rc3b3week

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.rc3b3week.databinding.ItemChatTextBinding

private lateinit var binding: ItemChatTextBinding

class ChatAdapter(private val context: Context, private val chatList: ArrayList<Chat>) : BaseAdapter() {


    //바인딩에 들어갈 인플레이트를 만들어 줘야함
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    // list 내용물의 갯수
    override fun getCount(): Int = chatList.size

    // list에 있는 p0위치의 값을 가져옴
    override fun getItem(p0: Int): Any =chatList[p0]

    //p0 는 position 즉, 위치라고 생각하면됨
    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        binding = ItemChatTextBinding.inflate(inflater, p2, false)

        binding.tvItemChat.text = chatList[p0].chatText
        binding.tvChatTime.text = chatList[p0].chatTime


        return binding.root
    }


}