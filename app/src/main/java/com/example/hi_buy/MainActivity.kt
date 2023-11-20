package com.example.hi_buy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var btn1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById<Button>(R.id.btn1)

        btn1.setOnClickListener {
            searchData()
        }
    }

    private fun searchData() {

        //retrofit build
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addConverterFactory(ScalarsConverterFactory.create()) //스칼라가 먼저여야함
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        //RetrofitService 객체 생성
        val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)


        //검색
        val call: Call<NaverSearchApiResponse> =
            retrofitService.searchDataByJson("건강기능식품")

        call.enqueue(object : Callback<NaverSearchApiResponse> {
            override fun onResponse(
                call: Call<NaverSearchApiResponse>,
                response: Response<NaverSearchApiResponse>
            ) {
                val naverResponse: NaverSearchApiResponse? = response.body()

                println("naverResponse = ${naverResponse}")
                println(naverResponse!!.items[0])
                println(naverResponse!!.items[1].title)

                //확인용 토스트
                Toast.makeText(this@MainActivity, "아이템 개수 : ${naverResponse?.items?.size}", Toast.LENGTH_SHORT).show()


                //응답받은 객체의 items s리스트를 리사이클러뷰에 보이기
                /*binding.recycler.adapter =
                    MyAdapter(this@MainActivity, naverResponse!!.items) //nullable*/
                //setAdapter 하면 그게 Notify임 따로 안해도 된다
            }

            override fun onFailure(call: Call<NaverSearchApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "에러 : " + t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}