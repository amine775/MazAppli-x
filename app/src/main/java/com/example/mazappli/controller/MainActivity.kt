package com.example.mazappli.controller

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.example.mazappli.R
import com.example.mazappli.schemaclass.WeatherClass
import com.example.mazappli.schemaclass.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : FragmentActivity(), LonLatFragment.ButListener {


    private var weatherData: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onButtonClick(ville: String) {
        val textFragment = supportFragmentManager.findFragmentById(
            R.id.fragment3
        ) as TextFragment
         Log.d("onButtonClicked","ville : $ville")
        getCurrentData(ville)
        textFragment.changeTextProperties(weatherData)
    }



    private fun getCurrentData(ville: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(ville, AppId)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {

                    val weatherResponse = response.body()!!
                    val objectWeather = WeatherClass()

                    objectWeather.ville=ville
                    objectWeather.temp= (weatherResponse.main!!.temp-273.15).toString()                 //We convert from kelvin to Celcius
                    objectWeather.temp_min= (weatherResponse.main!!.temp_min-273.15).toString()
                    objectWeather.temp_max= (weatherResponse.main!!.temp_max-273.15).toString()
                    objectWeather.pays=weatherResponse.sys!!.country




                    weatherData = "A ${objectWeather.ville} (${objectWeather.pays}), \n il fait actuellement ${objectWeather.temp} °C. \n Il fait au plus bas ${objectWeather.temp_min} °C. \n Il fait au plus haut ${objectWeather.temp_max} °C"

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






