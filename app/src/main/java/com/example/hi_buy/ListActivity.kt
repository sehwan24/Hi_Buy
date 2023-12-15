package com.example.hi_buy

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.medicine_list)

        var Add = findViewById<Button>(R.id.btnAdd)
        Add.setOnClickListener {
            //제품추가 화면으로 넘어가기
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        //제품추가 이름 인텐트 가져오기
        var mName = intent.getStringExtra("mname")
        // 리스트 구현
        var mList = ArrayList<String>()
        var list = findViewById<ListView>(R.id.mlistView)
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mList)
        list.adapter = adapter

        if (mName != null) {
            mList.add(mName)
            adapter.notifyDataSetChanged()
        }
        mList.add("비타민C")
        mList.add("오메가3")
        mList.add("루테인")
        //리스트 길게 누를시 제거
        list.setOnItemLongClickListener { parent, view, position, id ->
            mList.removeAt(position)
            adapter.notifyDataSetChanged()
            false
        }

    }

}