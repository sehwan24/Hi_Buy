package com.example.hi_buy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.hi_buy.MainActivity
import com.example.hi_buy.R

class AddActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.medicine_add)

        //스피너 추가
        val addspin1 = findViewById<Spinner>(R.id.Addspin1)
        addspin1.adapter = ArrayAdapter.createFromResource(this, R.array.itemList, android.R.layout.simple_spinner_item)
        val addspin2 = findViewById<Spinner>(R.id.Addspin2)
        addspin2.adapter = ArrayAdapter.createFromResource(this, R.array.itemList, android.R.layout.simple_spinner_item)

        var mName = findViewById(R.id.editmName) as EditText
        var AddFinish = findViewById<Button>(R.id.btnAddFinish)

        //완료버튼누를시
        AddFinish.setOnClickListener {
            //리스트로 제품이름 인텐트
            var intent =  Intent(applicationContext, ListActivity::class.java)
            intent.putExtra("mname", mName.text.toString())
            startActivity(intent)
            //리스트로 돌아가기
            finish()
            //제품추가 Toast
            var AddFinishMsg = Toast.makeText(applicationContext, "제품을 추가했습니다.", Toast.LENGTH_SHORT)
            AddFinishMsg.show()
        }
    }
}