package com.example.mazappli.controller

import com.example.mazappli.schemaclass.Ville
import retrofit2.Call
import retrofit2.http.GET


interface WeatherAPI{
    @GET("location/city?token=5d5372307f849c06d315eb3d8a14d712f37cdbbc84837bdb81345ca978ed4e52&insee=35238")
    fun getVille(): Call<Ville>
}