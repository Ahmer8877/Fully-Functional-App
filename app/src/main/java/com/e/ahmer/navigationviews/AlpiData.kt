package com.e.ahmer.navigationviews

import retrofit2.Call
import retrofit2.http.GET

//interface class
interface AlpiData {

    //get data from API (products)
    @GET("products")
     fun ApiData() : Call<My>
}