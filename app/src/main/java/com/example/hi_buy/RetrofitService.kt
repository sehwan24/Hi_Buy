package com.example.hi_buy

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {


    //리턴은 Call <> 제네릭 안에 는 쿼리에서 받을 것



    //헤더 값들도 안바뀌는 글자이기 때문에 고정하기
    @Headers("X-Naver-Client-Id: RNzbka6EXecpTVBbXdWd","X-Naver-Client-Secret: D8UyvylyfJ")  //gitignore todo
    @GET("/v1/search/shop.json?display=20")   //20개씩
    fun searchDataByJson(@Query("query") query:String): Call<NaverSearchApiResponse>


}