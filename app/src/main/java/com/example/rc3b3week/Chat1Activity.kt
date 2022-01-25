package com.example.rc3b3week

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rc3b3week.databinding.ActivityChat1Binding
import java.text.SimpleDateFormat
import java.util.*

import android.preference.PreferenceManager
import kotlin.collections.ArrayList
import android.content.SharedPreferences
import java.lang.reflect.Type
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.content.Intent
import org.json.JSONArray


private lateinit var binding:ActivityChat1Binding
data class Chat(val chatText: String?, val chatTime: String?)
private lateinit var cadapter :ChatAdapter
private var listPosition: Int = 0
private var listPositionTop: Int = 0
private const val SETTINGS_PLAYER_JSON: String = "settings_item_json"

class Chat1Activity : AppCompatActivity() {
    private var chatList = arrayListOf<Chat>()
    var mNow: Long = 0
    var mDate: Date? = null
    var mFormat: SimpleDateFormat = SimpleDateFormat("hh:mm")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChat1Binding.inflate(layoutInflater)

        cadapter = ChatAdapter(this,chatList)
        binding.lvChat.adapter = cadapter
        binding.ibSend.setOnClickListener {
            //var count: Int = cadapter.count
            var text :String = binding.etChat.text.toString()
            if(text != ""){
                chatList.add(Chat(" $text ", getTime()))
                cadapter.notifyDataSetChanged();
                binding.etChat.setText("")
            }
        }
        binding.ibChatBack.setOnClickListener {
            var count :Int = chatList.size
            var text: String = chatList[count-1].chatText.toString()
            var time: String = chatList[count-1].chatTime.toString()
            val intent = Intent()
            intent.putExtra("my_text", text)
            intent.putExtra("my_time", time)
            setResult(RESULT_OK, intent)
            finish()
        }
        setContentView(binding.root)
    }
    private fun getTime(): String? {
        mNow = System.currentTimeMillis()
        mDate = Date(mNow)
        return mFormat.format(mDate)
    }



    override fun onResume() {
        super.onResume()
        for (friend in ReadFriendsData()!!) {
            chatList.add(Chat(friend?.chatText, friend?.chatTime))
        }
    }

    override fun onStop() {
        super.onStop()
        SaveFriendData(chatList)
    }

    private fun SaveFriendData(friends: ArrayList<Chat>) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(
            applicationContext
        )
        val editor: SharedPreferences.Editor = preferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(friends)
        editor.putString("MyFriends", json)
        editor.commit()
    }

    private fun ReadFriendsData(): ArrayList<Chat?>? {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(
            applicationContext
        )
        val gson = Gson()
        val a = JSONArray()
        val json = sharedPrefs.getString("MyFriends", a.toString())
        val type: Type = object : TypeToken<ArrayList<Chat?>?>() {}.type
        return gson.fromJson(json, type)
    }



}


