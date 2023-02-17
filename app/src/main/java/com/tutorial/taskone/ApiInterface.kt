package com.tutorial.taskone

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("services/rest/?method=flickr.galleries.getPhotos&api_key=f9736f4d370f9c7115a952951b506569&gallery_id=66911286-72157647277042064&format=json&nojsoncallback=1")
    fun getData(): Call<MyData>
}