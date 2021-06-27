package com.remote_control_joystick.model

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RemoteModel {

    private var client: Client

    private var executor: ExecutorService


    //create thread pool with one thread
    constructor() {
        this.client = Client()
        this.executor = Executors.newFixedThreadPool(1)
    }

    //connect to Flight Gear
    fun connect(ip: String, port: Int) {
        client.connect(ip, port)
    }


    //send data to Flight Gear
    fun sendData(data: String) {
        val worker = Runnable { client.sendData(data) }
        executor.execute(worker)
    }


}