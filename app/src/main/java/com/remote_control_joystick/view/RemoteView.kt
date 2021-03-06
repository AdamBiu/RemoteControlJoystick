package com.remote_control_joystick.view

import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.remote_control_joystick.view.Joystick
import com.remote_control_joystick.viewModel.RemoteViewModel
import com.remote_control_joystick.databinding.ActivityMainBinding


open class RemoteView {

    private lateinit var rViewModel: RemoteViewModel
    private lateinit var dataBinding: ActivityMainBinding

    private var midRubber: Float


    //set listener for both seek bar and build the joystick
    constructor(binding: ActivityMainBinding, rViewModel: RemoteViewModel) {
        this.dataBinding = binding
        this.rViewModel = rViewModel
        Joystick(this.rViewModel, dataBinding.joypadView)

        midRubber = dataBinding.seekBarRudder.max.toFloat() / 2
        initThrottleSeekBar()
        initRudderSeekBar()


    }

    //Listener for throttle
    private fun initThrottleSeekBar() {
        dataBinding.seekBarThrottle.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                rViewModel.sendData("set /controls/engines/current-engine/throttle ${i.toFloat() / 100}")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    //Listener for rudder
    private fun initRudderSeekBar() {
        dataBinding.seekBarRudder.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

                var num: Float = 0F

                if (i < midRubber) {
                    num = -(1 - (i.toFloat() / midRubber))
                } else if (i > midRubber) {
                    num = (i - midRubber).toFloat() / midRubber
                }

                rViewModel.sendData("set /controls/flight/rudder $num")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }
}