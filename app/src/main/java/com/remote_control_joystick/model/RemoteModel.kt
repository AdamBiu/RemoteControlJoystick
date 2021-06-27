package com.remote_control_joystick.model

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RemoteModel {

    private var client: Client

    private var executor: ExecutorService


    constructor() {
        this.client = Client()

        this.executor = Executors.newFixedThreadPool(1)
//        for (i in 0..9) {
//            val worker = Runnable { println("Hello this is thread " + i) }
//            executor.execute(worker)
//        }
//        executor.shutdown()
//        while (!executor.isTerminated) {
//        }
//        println("Finished all threads")

    }

    fun connect(ip: String, port: Int) {
        client.connect(ip, port)
    }


    fun sendData(data: String) {
        val worker = Runnable { client.sendData(data) }
        executor.execute(worker)
    }


}