package com.example.rc3b3week

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rc3b3week.databinding.ActivityMainBinding
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.R
import android.R.attr
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.R.attr.data
import android.app.Activity
import androidx.annotation.Nullable


private lateinit var binding:ActivityMainBinding

data class Person (val profileImage : Int, val name : String, val count : String, var chat : String, var time : String)

open class MainActivity : AppCompatActivity() {
    private var personList = arrayListOf<Person>()
    private lateinit var myadapter :MAdapter
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val header = layoutInflater.inflate(com.example.rc3b3week.R.layout.listview_header, null, false)
        val footer = layoutInflater.inflate(com.example.rc3b3week.R.layout.listview_footer, null, false)
        setSupportActionBar(binding.tbTitle)
        myadapter= MAdapter(this,personList)
        for(i in 1..3){
            personList.add(Person(com.example.rc3b3week.R.drawable.user_icon,"맥스", "", "최근에온말", "오후 8:02"))
            personList.add(Person(com.example.rc3b3week.R.drawable.user_icon2, "덴고", "2", "Hello Android", "오전 11:49"))
            personList.add(Person(com.example.rc3b3week.R.drawable.user_icon3, "맥스, 올리비아, 재키,...", "7", "Hello Android", "오전 11:49"))
            personList.add(Person(com.example.rc3b3week.R.drawable.user_icon4, "동아리", "11", "Hello Android", "오전 11:49"))
            personList.add(Person(com.example.rc3b3week.R.drawable.user_icon5, "스터디", "3", "Hello Android", "오전 11:49"))
        }
        binding.lvPhone.addHeaderView(header)
        binding.lvPhone.addFooterView(footer)
        binding.lvPhone.adapter = myadapter
        binding.lvPhone.setOnItemClickListener { adapterView, view, i, l ->
            if(i == 1){
                val intent = Intent(this, Chat1Activity::class.java)
                startActivityForResult(intent, 5678);
            }
            //채팅창 액티비티 띄워줄거임
        }
        val dialog = CustomDialog(this)

        binding.lvPhone.setOnItemLongClickListener { adapterView, view, i, l ->

            dialog.setDialogListener(object : MyDialogListener {
                override fun onPositiveClicked(num: String?) {
                    if (num != null) {
                        setResult(num,i-1)
                    }
                }
                override fun onNegativeClicked() {
                }
            })
            dialog.show(supportFragmentManager, "CustomDialog")
            true
        }

        setContentView(binding.root)
    }

    private fun setResult(num: String,po : Int) {

        if(num == "삭제"){
            Toast.makeText(this,"${personList[po].name}채팅방을 나왔습니다.",Toast.LENGTH_LONG).show()
            personList.removeAt(po)
            myadapter.notifyDataSetChanged()
        }
        else{
            Toast.makeText(this,"값이 안받아짐",Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.rc3b3week.R.menu.item_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item?.itemId){
            com.example.rc3b3week.R.id.it_chat ->{
                val intent = Intent(this, NewChatActivity::class.java)
                startActivityForResult(intent,1234)
                super.onOptionsItemSelected(item)
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var count:Int = personList.size
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            val myData: String? = data?.getStringExtra("my_data")
            myData?.let {
                Person(com.example.rc3b3week.R.drawable.user_icon,
                    it, "","새로 생성된 채팅방","오후 9:02")
            }?.let { personList.add(it) }
        }
        else if (requestCode == 5678 && resultCode == RESULT_OK) {
            var mytext = data?.getStringExtra("my_text")
            var mytime = data?.getStringExtra("my_time")
            personList[0].chat = mytext!!
            personList[0].time = mytime!!
        }
    }

    /*private fun saveState(){
        val pref = getSharedPreferences("pref", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("text", chatList.toString())
        editor.commit()
    }
    private fun restoreState(){
        val pref = getSharedPreferences("pref", MODE_PRIVATE)
        if (pref != null && pref.contains("id")) {
            val textItem = pref.getString("text", "")
            chatList.add(textItem)
            //binding.etIdInput.setText(id)
            //val pw = pref.getString("pw", "")
            //binding.etPasswordInput.setText(pw)
        }
    }
    private fun clearState() {
        val pref = getSharedPreferences("pref", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isFirst",false)
        editor.putString("id",null)
        editor.putString("pw",null)
        editor.commit()
    }*/
}

