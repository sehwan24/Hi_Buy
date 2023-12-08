package com.example.hi_buy

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RewardActivity : AppCompatActivity() {
    lateinit var reward_btn : ImageView
    lateinit var fin_btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_reward)

        reward_btn = findViewById(R.id.reward_btn)

        reward_btn.setOnClickListener {
            setContentView(R.layout.reward_delivery)
            fin_btn = findViewById(R.id.fin_btn)
            fin_btn.setOnClickListener {
                setContentView(R.layout.reward_finish)
            }
        }



    }
}