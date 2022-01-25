package com.example.rc3b3week

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rc3b3week.databinding.ActivityNewchatBinding

private lateinit var binding: ActivityNewchatBinding

class NewChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);

        binding = ActivityNewchatBinding.inflate(layoutInflater)

        binding.btNomalChat.setOnClickListener{
            val intent = Intent(this, PlusMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
            startActivity(intent)
            finish()
        }

        setContentView(binding.root)
    }
}