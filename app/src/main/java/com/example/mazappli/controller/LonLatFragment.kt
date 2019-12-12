package com.example.mazappli.controller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mazappli.R
import kotlinx.android.synthetic.main.lonlat_fragment.*

/**
 * Created by <name> on <date>.
 */
class LonLatFragment : Fragment() {

    var activityCallback: ButListener?=null

    interface ButListener {
        fun onButtonClick(lon:String, lat:String)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater?.inflate(
            R.layout.lonlat_fragment,
            container, false)

        val button: Button? = view?.findViewById(R.id.button1)

        button?.setOnClickListener { v: View -> buttonClicked(v)}

        return view
    }


    private fun buttonClicked(view: View) {
        activityCallback?.onButtonClick(lon_text.text.toString(), lat_text.text.toString())
    }

}