package com.example.mazappli.controller

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.mazappli.R
import com.example.mazappli.schemaclass.WeatherClass
import com.example.mazappli.schemaclass.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.round

class MainActivity : FragmentActivity(), LonLatFragment.ButListener {


    private var weatherData: String? = null
    var objectWeather = WeatherClass()
    var draw1: Drawable?=null
    var counter : Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onButtonClick(ville: String) {

        this.getCurrentData(ville)

        val textFragment = supportFragmentManager.findFragmentById(
            R.id.fragment3
        ) as TextFragment
        counter += 1


        if (counter < 2 && weatherData!=null){
            textFragment.changeTextProperties(weatherData, draw1)
            weatherData=null
            draw1 = null
            counter = 0
        } else {
            textFragment.changeTextProperties(
                text = "An error occured\nPlease verify your connection\nOr try an other city",
                img = draw1
            )
            counter = 0
        }

    }



    private fun getCurrentData(ville: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(ville, AppId,"fr")
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {

                    val weatherResponse = response.body()!!

                    objectWeather.ville=ville
                    objectWeather.temp= (round(weatherResponse.main!!.temp-273.15)).toString()                //We convert from kelvin to Celcius
                    objectWeather.temp_cloud= weatherResponse.clouds!!.all
                    if (objectWeather.temp_cloud!! > 20.0 && objectWeather.temp_cloud!! <70){
                        draw1 =  ContextCompat.getDrawable(applicationContext, R.drawable.nuage)
                    } else if (objectWeather.temp_cloud!! >= 70.0){
                        draw1 = ContextCompat.getDrawable(applicationContext, R.drawable.nuage2)
                    } else if (objectWeather.temp_cloud!! <= 20.0){
                        draw1 = ContextCompat.getDrawable(applicationContext, R.drawable.soleil)
                    }
                    objectWeather.temp_humidity = (weatherResponse.main!!.humidity).toString()
                    objectWeather.temp_wind= (round(weatherResponse.wind!!.speed*3600/1000))                  //We convert from m/s to km/h
                    if (objectWeather.temp_wind!! >= 41.0){
                        draw1= ContextCompat.getDrawable(applicationContext, R.drawable.vent)
                    }
                    objectWeather.temp_description= weatherResponse.weather.get(0).description
                    if(objectWeather.temp_description!!.contains("pluie", ignoreCase = true)){
                        draw1= ContextCompat.getDrawable(applicationContext, R.drawable.pluie)
                    }
                    objectWeather.pays= weatherResponse.sys!!.country




                    weatherData = "${objectWeather.ville} (${objectWeather.pays}),\n${objectWeather.temp_description!!.toUpperCase()},\n il fait actuellement ${objectWeather.temp} °C. \n Le vent souffle a ${objectWeather.temp_wind.toString()}km/h. \n Nuage :${objectWeather.temp_cloud.toString()}% de recouvrement. \n Humidité :${objectWeather.temp_humidity}% "

                    Log.d("onResponse", "results = $weatherData")
                } 
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("onFailure", "Failure : ${t.message}")
                weatherData = t.message
            }
        })
    }

    companion object {

        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "16deedf64510aae17dc6cb07169ef5f7"
    }

}






