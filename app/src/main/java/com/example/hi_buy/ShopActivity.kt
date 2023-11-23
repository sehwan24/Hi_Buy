package com.example.hi_buy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ShopActivity : AppCompatActivity() {

    lateinit var search_item_et : EditText
    lateinit var search_btn : ImageButton
    lateinit var my_shop_btn : ImageButton
    lateinit var search_item_et2 : EditText
    lateinit var search_btn2 : ImageButton
    lateinit var my_shop_btn2 : ImageButton
    lateinit var ad1_btn : ImageButton
    lateinit var recommend_btn : Button
    lateinit var rec1_btn : ImageButton
    lateinit var rec1_tv1 : TextView
    lateinit var rec1_tv2 : TextView
    lateinit var rec2_btn : ImageButton
    lateinit var rec2_tv1 : TextView
    lateinit var rec2_tv2 : TextView
    lateinit var rec3_btn : ImageButton
    lateinit var rec3_tv1 : TextView
    lateinit var rec3_tv2 : TextView
    lateinit var rec4_btn : ImageButton
    lateinit var rec4_tv1 : TextView
    lateinit var rec4_tv2 : TextView
    lateinit var rec5_btn : ImageButton
    lateinit var rec5_tv1 : TextView
    lateinit var rec5_tv2 : TextView
    lateinit var vitamin_btn : Button
    lateinit var vit1_btn : ImageButton
    lateinit var vit1_tv1 : TextView
    lateinit var vit1_tv2 : TextView
    lateinit var vit2_btn : ImageButton
    lateinit var vit2_tv1 : TextView
    lateinit var vit2_tv2 : TextView
    lateinit var vit3_btn : ImageButton
    lateinit var vit3_tv1 : TextView
    lateinit var vit3_tv2 : TextView
    lateinit var vit4_btn : ImageButton
    lateinit var vit4_tv1 : TextView
    lateinit var vit4_tv2 : TextView
    lateinit var vit5_btn : ImageButton
    lateinit var vit5_tv1 : TextView
    lateinit var vit5_tv2 : TextView
    lateinit var res1_btn : ImageButton
    lateinit var res1_tv1 : TextView
    lateinit var res1_tv2 : TextView
    lateinit var res1_tv3 : TextView
    lateinit var res2_btn : ImageButton
    lateinit var res2_tv1 : TextView
    lateinit var res2_tv2 : TextView
    lateinit var res2_tv3 : TextView
    lateinit var res3_btn : ImageButton
    lateinit var res3_tv1 : TextView
    lateinit var res3_tv2 : TextView
    lateinit var res3_tv3 : TextView
    lateinit var res4_btn : ImageButton
    lateinit var res4_tv1 : TextView
    lateinit var res4_tv2 : TextView
    lateinit var res4_tv3 : TextView
    lateinit var res5_btn : ImageButton
    lateinit var res5_tv1 : TextView
    lateinit var res5_tv2 : TextView
    lateinit var res5_tv3 : TextView
    lateinit var res6_btn : ImageButton
    lateinit var res6_tv1 : TextView
    lateinit var res6_tv2 : TextView
    lateinit var res6_tv3 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_home)


        search_item_et = findViewById<EditText>(R.id.search_item_et)
        search_btn = findViewById<ImageButton>(R.id.search_btn)
        my_shop_btn = findViewById<ImageButton>(R.id.my_shop_btn)
        ad1_btn = findViewById<ImageButton>(R.id.ad1_btn)
        recommend_btn = findViewById<Button>(R.id.recommend_btn)
        rec1_btn = findViewById<ImageButton>(R.id.rec1_btn)
        rec1_tv1 = findViewById<TextView>(R.id.rec1_tv1)
        rec1_tv2 = findViewById<TextView>(R.id.rec1_tv2)
        rec2_btn = findViewById<ImageButton>(R.id.rec2_btn)
        rec2_tv1 = findViewById<TextView>(R.id.rec2_tv1)
        rec2_tv2 = findViewById<TextView>(R.id.rec2_tv2)
        rec3_btn = findViewById<ImageButton>(R.id.rec3_btn)
        rec3_tv1 = findViewById<TextView>(R.id.rec3_tv1)
        rec3_tv2 = findViewById<TextView>(R.id.rec3_tv2)
        rec4_btn = findViewById<ImageButton>(R.id.rec4_btn)
        rec4_tv1 = findViewById<TextView>(R.id.rec4_tv1)
        rec4_tv2 = findViewById<TextView>(R.id.rec4_tv2)
        rec5_btn = findViewById<ImageButton>(R.id.rec5_btn)
        rec5_tv1 = findViewById<TextView>(R.id.rec5_tv1)
        rec5_tv2 = findViewById<TextView>(R.id.rec5_tv2)

        vitamin_btn = findViewById<Button>(R.id.vitamin_btn)
        vit1_btn = findViewById<ImageButton>(R.id.vit1_btn)
        vit1_tv1 = findViewById<TextView>(R.id.vit1_tv1)
        vit1_tv2 = findViewById<TextView>(R.id.vit1_tv2)
        vit2_btn = findViewById<ImageButton>(R.id.vit2_btn)
        vit2_tv1 = findViewById<TextView>(R.id.vit2_tv1)
        vit2_tv2 = findViewById<TextView>(R.id.vit2_tv2)
        vit3_btn = findViewById<ImageButton>(R.id.vit3_btn)
        vit3_tv1 = findViewById<TextView>(R.id.vit3_tv1)
        vit3_tv2 = findViewById<TextView>(R.id.vit3_tv2)
        vit4_btn = findViewById<ImageButton>(R.id.vit4_btn)
        vit4_tv1 = findViewById<TextView>(R.id.vit4_tv1)
        vit4_tv2 = findViewById<TextView>(R.id.vit4_tv2)
        vit5_btn = findViewById<ImageButton>(R.id.vit5_btn)
        vit5_tv1 = findViewById<TextView>(R.id.vit5_tv1)
        vit5_tv2 = findViewById<TextView>(R.id.vit5_tv2)

        searchData()

        search_btn.setOnClickListener {
            searchData(search_item_et.text.toString())
            setContentView(R.layout.search_result)

            search_item_et2 = findViewById<EditText>(R.id.search_item_et2)
            search_btn2 = findViewById<ImageButton>(R.id.search_btn2)
            my_shop_btn2 = findViewById<ImageButton>(R.id.my_shop_btn2)
            res1_btn = findViewById<ImageButton>(R.id.res1_btn)
            res1_tv1 = findViewById<TextView>(R.id.res1_tv1)
            res1_tv2 = findViewById<TextView>(R.id.res1_tv2)
            res1_tv3 = findViewById<TextView>(R.id.res1_tv3)
            res2_btn = findViewById<ImageButton>(R.id.res2_btn)
            res2_tv1 = findViewById<TextView>(R.id.res2_tv1)
            res2_tv2 = findViewById<TextView>(R.id.res2_tv2)
            res2_tv3 = findViewById<TextView>(R.id.res2_tv3)
            res3_btn = findViewById<ImageButton>(R.id.res3_btn)
            res3_tv1 = findViewById<TextView>(R.id.res3_tv1)
            res3_tv2 = findViewById<TextView>(R.id.res3_tv2)
            res3_tv3 = findViewById<TextView>(R.id.res3_tv3)
            res4_btn = findViewById<ImageButton>(R.id.res4_btn)
            res4_tv1 = findViewById<TextView>(R.id.res4_tv1)
            res4_tv2 = findViewById<TextView>(R.id.res4_tv2)
            res4_tv3 = findViewById<TextView>(R.id.res4_tv3)
            res5_btn = findViewById<ImageButton>(R.id.res5_btn)
            res5_tv1 = findViewById<TextView>(R.id.res5_tv1)
            res5_tv2 = findViewById<TextView>(R.id.res5_tv2)
            res5_tv3 = findViewById<TextView>(R.id.res5_tv3)
            res6_btn = findViewById<ImageButton>(R.id.res6_btn)
            res6_tv1 = findViewById<TextView>(R.id.res6_tv1)
            res6_tv2 = findViewById<TextView>(R.id.res6_tv2)
            res6_tv3 = findViewById<TextView>(R.id.res6_tv3)

            search_btn2.setOnClickListener {
                println(search_item_et2.text.toString())
                searchData(search_item_et2.text.toString())
            }
        }




    }

    private fun searchData(str: String) {

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
            retrofitService.searchDataByJson(str)


        call.enqueue(object : Callback<NaverSearchApiResponse> {
            override fun onResponse(
                call: Call<NaverSearchApiResponse>,
                response: Response<NaverSearchApiResponse>
            ) {
                val naverResponse: NaverSearchApiResponse? = response.body()

                //rec1_btn.setImageResource(naverResponse!!.items[0].link)
                Glide.with(this@ShopActivity).load(naverResponse!!.items[0].image).into(res1_btn)
                res1_tv1.setText(naverResponse!!.items[0].title)
                res1_tv2.setText(naverResponse!!.items[0].lprice)
                res1_tv3.setText(naverResponse!!.items[0].mallName)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[1].image).into(res2_btn)
                res2_tv1.setText(naverResponse!!.items[1].title)
                res2_tv2.setText(naverResponse!!.items[1].lprice)
                res2_tv3.setText(naverResponse!!.items[1].mallName)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[2].image).into(res3_btn)
                res3_tv1.setText(naverResponse!!.items[2].title)
                res3_tv2.setText(naverResponse!!.items[2].lprice)
                res3_tv3.setText(naverResponse!!.items[2].mallName)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[3].image).into(res4_btn)
                res4_tv1.setText(naverResponse!!.items[3].title)
                res4_tv2.setText(naverResponse!!.items[3].lprice)
                res4_tv3.setText(naverResponse!!.items[3].mallName)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[4].image).into(res5_btn)
                res5_tv1.setText(naverResponse!!.items[4].title)
                res5_tv2.setText(naverResponse!!.items[4].lprice)
                res5_tv3.setText(naverResponse!!.items[4].mallName)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[4].image).into(res6_btn)
                res6_tv1.setText(naverResponse!!.items[4].title)
                res6_tv2.setText(naverResponse!!.items[4].lprice)
                res6_tv3.setText(naverResponse!!.items[4].mallName)

                println("naverResponse = ${naverResponse}")
                println(naverResponse!!.items[0])
                println(naverResponse!!.items[1].title)

                //확인용 토스트
                //Toast.makeText(this@ShopActivity, "아이템 개수 : ${naverResponse?.items?.size}", Toast.LENGTH_SHORT).show()


            }

            override fun onFailure(call: Call<NaverSearchApiResponse>, t: Throwable) {
                Toast.makeText(this@ShopActivity, "에러 : " + t.message, Toast.LENGTH_SHORT).show()
            }

        })
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
            retrofitService.searchDataByJson("건강보조식품")

        val call2: Call<NaverSearchApiResponse> =
            retrofitService.searchDataByJson("멀티비타민")

        call.enqueue(object : Callback<NaverSearchApiResponse> {
            override fun onResponse(
                call: Call<NaverSearchApiResponse>,
                response: Response<NaverSearchApiResponse>
            ) {
                val naverResponse: NaverSearchApiResponse? = response.body()

                //rec1_btn.setImageResource(naverResponse!!.items[0].link)
                Glide.with(this@ShopActivity).load(naverResponse!!.items[0].image).into(rec1_btn)
                rec1_tv1.setText(naverResponse!!.items[0].title)
                rec1_tv2.setText(naverResponse!!.items[0].lprice)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[1].image).into(rec2_btn)
                rec2_tv1.setText(naverResponse!!.items[1].title)
                rec2_tv2.setText(naverResponse!!.items[1].lprice)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[2].image).into(rec3_btn)
                rec3_tv1.setText(naverResponse!!.items[2].title)
                rec3_tv2.setText(naverResponse!!.items[2].lprice)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[3].image).into(rec4_btn)
                rec4_tv1.setText(naverResponse!!.items[3].title)
                rec4_tv2.setText(naverResponse!!.items[3].lprice)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[4].image).into(rec5_btn)
                rec5_tv1.setText(naverResponse!!.items[4].title)
                rec5_tv2.setText(naverResponse!!.items[4].lprice)

                //println("naverResponse = ${naverResponse}")
                //println(naverResponse!!.items[0])
                //println(naverResponse!!.items[1].title)

                //확인용 토스트
                //Toast.makeText(this@ShopActivity, "아이템 개수 : ${naverResponse?.items?.size}", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<NaverSearchApiResponse>, t: Throwable) {
                Toast.makeText(this@ShopActivity, "에러 : " + t.message, Toast.LENGTH_SHORT).show()
            }

        })

        call2.enqueue(object : Callback<NaverSearchApiResponse> {
            override fun onResponse(
                call: Call<NaverSearchApiResponse>,
                response: Response<NaverSearchApiResponse>
            ) {
                val naverResponse: NaverSearchApiResponse? = response.body()

                //rec1_btn.setImageResource(naverResponse!!.items[0].link)
                Glide.with(this@ShopActivity).load(naverResponse!!.items[0].image).into(vit1_btn)
                vit1_tv1.setText(naverResponse!!.items[0].title)
                vit1_tv2.setText(naverResponse!!.items[0].lprice)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[1].image).into(vit2_btn)
                vit2_tv1.setText(naverResponse!!.items[1].title)
                vit2_tv2.setText(naverResponse!!.items[1].lprice)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[2].image).into(vit3_btn)
                vit3_tv1.setText(naverResponse!!.items[2].title)
                vit3_tv2.setText(naverResponse!!.items[2].lprice)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[3].image).into(vit4_btn)
                vit4_tv1.setText(naverResponse!!.items[3].title)
                vit4_tv2.setText(naverResponse!!.items[3].lprice)

                Glide.with(this@ShopActivity).load(naverResponse!!.items[4].image).into(vit5_btn)
                vit5_tv1.setText(naverResponse!!.items[4].title)
                vit5_tv2.setText(naverResponse!!.items[4].lprice)

                //println("naverResponse = ${naverResponse}")
                //println(naverResponse!!.items[0])
                //println(naverResponse!!.items[1].title)

                //확인용 토스트
                //Toast.makeText(this@ShopActivity, "아이템 개수 : ${naverResponse?.items?.size}", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<NaverSearchApiResponse>, t: Throwable) {
                Toast.makeText(this@ShopActivity, "에러 : " + t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}