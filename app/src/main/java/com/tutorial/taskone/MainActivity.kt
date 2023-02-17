package com.tutorial.taskone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://www.flickr.com/"
class MainActivity : AppCompatActivity() {
    var id:String?=null
    var farm:String?=null
    var server:String?=null
    var secret:String?=null
    var title:String?=null
    var urlList:ArrayList<String>?= arrayListOf()
    var titleList:ArrayList<String>?= arrayListOf()
    lateinit var myAdapter: MyAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        val gridLayout= GridLayoutManager(this,2)
        recyclerView.layoutManager=gridLayout


        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)


        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                val responseBody=response.body()!!
                for(item in responseBody.photos.photo.indices){
                    id=responseBody.photos.photo[item].id
                    farm=responseBody.photos.photo[item].farm.toString()
                    server=responseBody.photos.photo[item].server
                    secret=responseBody.photos.photo[item].secret
                    title=responseBody.photos.photo[item].title

                    val url="https://farm"+farm.toString()+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg"
                    urlList?.add(url)
                    titleList?.add(title!!)
                   // Log.d("niha", urlList.toString())


                }
               Log.d("niha", titleList.toString())
               // myAdapter= titleList?.let { MyAdapter(baseContext, it) }!!
               myAdapter= urlList?.let { titleList?.let { it1 -> MyAdapter(baseContext, it, it1) } }!!
                myAdapter.notifyDataSetChanged()
                recyclerView.adapter=myAdapter

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("MainActivity", "onFailure:" + t.message)
            }
        })

    }
}