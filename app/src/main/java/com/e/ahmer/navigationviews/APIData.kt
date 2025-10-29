package com.e.ahmer.navigationviews

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIData : AppCompatActivity() {

    //lateinit vari myrecyclerview
    lateinit var myrecyclerview : RecyclerView
    //lateinit vari myadapter

    lateinit var myadapter : MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apidata)

        myrecyclerview=findViewById(R.id.myreccle)

        //retrofit code
        val retrofit= Retrofit.Builder()
            .baseUrl("https://jsonexamples.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlpiData::class.java)

        //then get data from interface class
        val retrofitdata=retrofit.ApiData()

        //OnResponse and OnFailure method

        retrofitdata.enqueue(object : Callback<My?> {

            override fun onResponse(call: Call<My?>, response: Response<My?>) {
                // if api call is a success, then use the data of API and show in your app
                val responseBody = response.body()
                val productList = responseBody?.products!!

                //set separate adapter and layout code
                myadapter = MyAdapter(this@APIData, productList)
                myrecyclerview.adapter = myadapter
                myrecyclerview.layoutManager = LinearLayoutManager(this@APIData)
            }

            override fun onFailure(call: Call<My?>, t: Throwable) {
                // if api call fails
                Log.d("APIData ", "onFailure: " + t.message)
            }
        })

    }
}