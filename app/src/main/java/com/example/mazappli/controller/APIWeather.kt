package com.example.mazappli.controller

import com.example.mazappli.schemaclass.Ville
import retrofit2.Call
import retrofit2.http.GET

interface APIWeather {
    @GET("ewp/arrets.json")
    fun getVille(): Call<Ville>
}