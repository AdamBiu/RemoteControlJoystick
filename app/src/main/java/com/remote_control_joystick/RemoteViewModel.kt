package com.remote_control_joystick

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.remote_control_joystick.databinding.ActivityMainBinding

class RemoteViewModel() : ViewModel() {

    private lateinit var rModel: RemoteModel
    private lateinit var dataBinding: ActivityMainBinding


    constructor(dataBinding: ActivityMainBinding, rModel: RemoteModel) : this() {
        this.rModel = rModel
        this.dataBinding = dataBinding
    }

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

    private fun displayMessage(msg: String) {
        val mySnackBar = Snackbar.make(dataBinding.connect, msg, 4000)
        mySnackBar.show()
    }

    fun sendData(data: String) {
        rModel.sendData(data)
    }

/* companion object {
     fun makeNumBetweenMinusOneToOne(myNum: Float, maxNam: Float): Float {
         var retNum: Float = 0F
         var midNum: Float = 0F

         if (myNum < midNum) {
             retNum = -(1 - (myNum.toFloat() / midNum))
         } else if (myNum > midNum) {
             retNum = (myNum - midNum).toFloat() / midNum
         }
         return myNum
     }
 }*/

}