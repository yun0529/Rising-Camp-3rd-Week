package com.example.rc3b3week

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rc3b3week.databinding.ActivityPlusMemberBinding
import android.content.Intent
import android.widget.ListView
import android.widget.Toast


data class PMember (val pm_profileImage : Int, val pm_name : String, var pm_check:Boolean)

var items: ArrayList<Preparation_Item> = arrayListOf<Preparation_Item>()
private lateinit var binding: ActivityPlusMemberBinding
private var pluseMemberList = arrayListOf<PMember>()
class PlusMemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding.lvPmMember.choiceMode = ListView.CHOICE_MODE_MULTIPLE;
        binding = ActivityPlusMemberBinding.inflate(layoutInflater)

        for(i in 1..5){
            pluseMemberList.add(PMember(com.example.rc3b3week.R.drawable.user_icon,"맥스",false))
            pluseMemberList.add(PMember(com.example.rc3b3week.R.drawable.user_icon2,"재키",false))
            pluseMemberList.add(PMember(com.example.rc3b3week.R.drawable.user_icon3,"올리비아",false))
            pluseMemberList.add(PMember(com.example.rc3b3week.R.drawable.user_icon4,"덴고",false))
            pluseMemberList.add(PMember(com.example.rc3b3week.R.drawable.user_icon5,"하니",false))
        }
        //initItems()
        binding.lvPmMember.adapter = PMAdapter(this, pluseMemberList)
        //val mAdapter = PMAdapter(items)
        //binding.lvPmMember.adapter = PMAdapter(items)

        binding.lvPmMember.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "$i 번째 친구가 선택됨",Toast.LENGTH_LONG).show()
        }
        binding.btCreate.setOnClickListener{
            var data = binding.etMember.text.toString()
            val intent = Intent()
            intent.putExtra("my_data", data);
            setResult(RESULT_OK,intent)
            finish()
        }


        setContentView(binding.root)
    }

    private fun initItems() {
        //items = ArrayList()
        //val arrayText = resources.obtainTypedArray(com.example.rc3b3week.R.array.restext)
        for (i in 0 until pluseMemberList.size) {
            var c = pluseMemberList[i].pm_profileImage
            var s = pluseMemberList[i].pm_name
            var b = false
            var item = Preparation_Item(c, s, b)
            items.add(item)
        }
        //pluseMemberList.recycle()
    }
}