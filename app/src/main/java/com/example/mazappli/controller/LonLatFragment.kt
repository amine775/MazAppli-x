package com.example.mazappli.controller

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mazappli.R
import kotlinx.android.synthetic.main.lonlat_fragment.*
import kotlinx.android.synthetic.main.lonlat_fragment.view.*

/**
 * Created by <name> on <date>.
 */
class LonLatFragment : Fragment() {

    var activityCallback: ButListener?=null

    interface ButListener {
        fun onButtonClick(ville:String)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try{
            activityCallback = context as ButListener
        } catch (e: ClassCastException){
            throw java.lang.ClassCastException(context?.toString()+"must implement ButListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(
            R.layout.lonlat_fragment,
            container, false)

        view.button1.setOnClickListener {
            var ville=ville_text.text.toString()
            buttonClicked(ville)
        }

           // Return the fragment view/layout
           return view

    }


    private fun buttonClicked(ville: String) {
        activityCallback?.onButtonClick(ville)
    }

}