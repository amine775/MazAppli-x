package com.example.mazappli.controller

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mazappli.R
import kotlinx.android.synthetic.main.text_fragment.*

class TextFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(
            R.layout.text_fragment,
            container, false)
    }
    fun changeTextProperties(text: String?, img: Drawable?)
    {
        text_frag.text = text
        textimg.setImageDrawable(img)
    }
}