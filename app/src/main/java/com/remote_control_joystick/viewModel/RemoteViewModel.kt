package com.remote_control_joystick.viewModel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.remote_control_joystick.model.RemoteModel
import com.remote_control_joystick.databinding.ActivityMainBinding

class RemoteViewModel() : ViewModel() {

    private lateinit var rModel: RemoteModel
    private lateinit var dataBinding: ActivityMainBinding


    //constructor for ViewModel
    constructor(dataBinding: ActivityMainBinding, rModel: RemoteModel) : this() {
        this.rModel = rModel
        this.dataBinding = dataBinding
    }

    //Check if the ip and port are valid
    fun connect() {

        if (dataBinding.txtBoxIP.text.toString() == "" ||
            dataBinding.txtBoxPort.text.toString() == ""
        ) {
            displayMessage("IP or Port are empty")
        } else if (!Patterns.IP_ADDRESS.matcher(dataBinding.txtBoxIP.text.toString()).matches()) {
            displayMessage("IP not valid")
        } else {

            rModel.connect(
                dataBinding.txtBoxIP.text.toString(),
                dataBinding.txtBoxPort.text.toString().toInt()
            )
        }
    }

    //message to display
    private fun displayMessage(msg: String) {
        val mySnackBar = Snackbar.make(dataBinding.connect, msg, 4000)
        mySnackBar.show()
    }

    //send data to the Flight Gear
    fun sendData(data: String) {
        rModel.sendData(data)
    }

}