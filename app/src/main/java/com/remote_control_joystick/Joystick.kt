package com.remote_control_joystick

import android.widget.TextView
import ninja.eigenein.joypad.JoypadView
import java.util.*

class Joystick : JoypadView.Listener {

    private lateinit var rViewModel: RemoteViewModel

    private lateinit var joypadView: JoypadView
    private lateinit var textView: TextView

    constructor(rViewModel: RemoteViewModel, textView: TextView, joypadView: JoypadView) {
        this.rViewModel = rViewModel
        this.joypadView = joypadView
        this.textView = textView

        joypadView.setListener(this)
    }


    override fun onUp() {
        rViewModel.sendData("set /controls/flight/elevator 0\r\nset /controls/flight/aileron 0")
    }

    override fun onMove(distancia: Float, x: Float, y: Float) {

        rViewModel.sendData("set /controls/flight/elevator $y\r\nset /controls/flight/aileron $x")

        textView.setText("p0: $distancia, x: $x, y: $y"); //To change body of created functions use File | Settings | File Templates.
        println("distancia: $distancia, x: $x, y: $y")//To change body of created functions use File | Settings | File Templates.


    }


}