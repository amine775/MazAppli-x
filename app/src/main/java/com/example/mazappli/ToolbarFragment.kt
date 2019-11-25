package com.example.mazappli

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.toolbar_fragment.*
import java.text.FieldPosition

/**
 * Created by <name> on <date>.
 */
class ToolbarFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    var seekvalue = 10
    var activityCallback: ToolbarFragment.ToolbarListener?=null

    interface ToolbarListener {
        fun onButtonClick(position: Int, text: String)

    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try{
            activityCallback = context as ToolbarListener
        } catch (e: ClassCastException) {
            throw java.lang.ClassCastException(context?.toString()+"must implement ToolbarListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.toolbar_fragment,
            container, false)

        val seekBar: SeekBar? = view?.findViewById(R.id.seekBar1)
        val button: Button? = view?.findViewById(R.id.button1)

        seekBar?.setOnSeekBarChangeListener(this)

        button?.setOnClickListener { v: View -> buttonClicked(v)}

        return view
    }


    private fun buttonClicked(view: View) {
        activityCallback?.onButtonClick(seekvalue, change_text.text.toString())

    }
    override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                   fromUser: Boolean) {
        seekvalue = progress
    }

    override fun onStartTrackingTouch(arg0: SeekBar) {
    }

    override fun onStopTrackingTouch(arg0: SeekBar) {
    }
}