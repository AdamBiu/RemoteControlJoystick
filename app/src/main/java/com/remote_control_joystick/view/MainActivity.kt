package com.remote_control_joystick.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager

import androidx.databinding.DataBindingUtil

import androidx.lifecycle.ViewModelProviders
import com.remote_control_joystick.R
import com.remote_control_joystick.viewModel.RemoteViewModelFactory
import com.remote_control_joystick.viewModel.RemoteViewModel

import com.remote_control_joystick.databinding.ActivityMainBinding
import com.remote_control_joystick.model.RemoteModel


class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var rViewModel: RemoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rModel = RemoteModel()


        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val factory = RemoteViewModelFactory(dataBinding, rModel)
        rViewModel = ViewModelProviders.of(this, factory).get(RemoteViewModel::class.java)
        dataBinding.remoteViewModel = rViewModel
        dataBinding.lifecycleOwner = this

        val view = RemoteView(dataBinding, rViewModel)


        dataBinding.txtBoxPort.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    dataBinding.connect.performClick()
                    true
                }
                else -> false
            }
        }


    }

    private fun hideSoftKeyboard(view: View) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun clickedConnect(view: View) {
        rViewModel.connect()
        hideSoftKeyboard(view)
        dataBinding.txtBoxIP.clearFocus();
        dataBinding.txtBoxPort.clearFocus();

    }


}