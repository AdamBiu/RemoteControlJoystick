package com.remote_control_joystick.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.remote_control_joystick.model.RemoteModel
import com.remote_control_joystick.databinding.ActivityMainBinding
import java.lang.IllegalArgumentException

class RemoteViewModelFactory(databinding: ActivityMainBinding, rModel: RemoteModel) :
    ViewModelProvider.Factory {
    private var myDatabinding: ActivityMainBinding = databinding
    private var myModel: RemoteModel = rModel

    //Singleton for RemoteViewModel
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RemoteViewModel::class.java)) {
            return RemoteViewModel(myDatabinding, myModel) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}