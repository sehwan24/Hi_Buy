package com.example.hi_buy

data class NaverSearchApiResponse(var total:Int, var display:Int, var items:MutableList<ShoppingItem>)

//검색결과 1개 데이터
data class ShoppingItem(
    var title:String,
    var link: String,
    var image: String,
    var lprice: String, //읽어올 Integer값이 빈값으로 오면 error 날 수 있기에 타입을 그냥 String으로 받자
    var hprice: String,
    var mallName: String,
)

