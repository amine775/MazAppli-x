package com.example.mazappli.controller

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.mazappli.R
import com.example.mazappli.schemaclass.Ville
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : FragmentActivity(),
    ToolbarFragment.ToolbarListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onButtonClick(fontsize: Int, text: String) {
        val textFragment = supportFragmentManager.findFragmentById(
            R.id.fragment3
        ) as TextFragment

        textFragment.changeTextProperties(text)
    }

    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val apiUrl = "https://api.meteo-concept.com/api/"


    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coindesk.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    val myAPI=retrofit.create(Ville::class.java)

    myAPI.getVille().enqueue(object : Callback<Ville> {
        override fun onFailure(call: retrofit2.Call<Ville>?, t: Throwable?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onResponse(
            call: retrofit2.Call<Ville>?,
            response: retrofit2.Response<Ville>
        ) {

            //progressBar.visibility = View.GONE
        }
    })




}





