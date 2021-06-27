package com.remote_control_joystick

import com.remote_control_joystick.RemoteViewModel
import ninja.eigenein.joypad.JoypadView

class Joystick : JoypadView.Listener {

    private lateinit var rViewModel: RemoteViewModel
    private lateinit var joypadView: JoypadView

    constructor(rViewModel: RemoteViewModel, joypadView: JoypadView) {
        this.rViewModel = rViewModel
        this.joypadView = joypadView

        joypadView.setListener(this)
    }


    override fun onUp() {
        rViewModel.sendData("set /controls/flight/elevator 0\r\nset /controls/flight/aileron 0")
    }

    override fun onMove(distancia: Float, x: Float, y: Float) {

        rViewModel.sendData("set /controls/flight/elevator $y\r\nset /controls/flight/aileron $x")
        println("distancia: $distancia, x: $x, y: $y")//To change body of created functions use File | Settings | File Templates.


    }


}