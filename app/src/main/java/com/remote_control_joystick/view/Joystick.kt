package com.remote_control_joystick.view

import com.remote_control_joystick.viewModel.RemoteViewModel
import ninja.eigenein.joypad.JoypadView

class Joystick : JoypadView.Listener {

    private lateinit var rViewModel: RemoteViewModel
    private lateinit var joypadView: JoypadView

    //constructor for the joystick
    constructor(rViewModel: RemoteViewModel, joypadView: JoypadView) {
        this.rViewModel = rViewModel
        this.joypadView = joypadView

        joypadView.setListener(this)
    }

    //set fun for onUp operation
    override fun onUp() {
        rViewModel.sendData("set /controls/flight/elevator 0\r\nset /controls/flight/aileron 0")
    }

    //set fun for onMove operation
    override fun onMove(distancia: Float, x: Float, y: Float) {

        rViewModel.sendData("set /controls/flight/elevator $y\r\nset /controls/flight/aileron $x")
        println("distancia: $distancia, x: $x, y: $y")//To change body of created functions use File | Settings | File Templates.


    }


}