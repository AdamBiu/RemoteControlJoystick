package com.remote_control_joystick

import android.util.Log
import java.io.PrintWriter
import java.net.ConnectException
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

const val TAG = "MainActivity"

class Client {

    // private var rModel: RemoteModel
    private lateinit var executor: ExecutorService
    private lateinit var socket: Socket
    private var printWriter: PrintWriter? = null
    //private var serverSocket: ServerSocket? = null


    /* constructor(model: RemoteModel) {
         this.rModel = model
     }*/

    fun connect(ip: String, port: Int) {

        if (this::executor.isInitialized) {

            this.executor.shutdownNow()
            Log.d(TAG, "shutdownNow the connect socket")
        }

        this.executor = Executors.newSingleThreadExecutor()

        executor.execute {
            try {
                Log.d(TAG, "socket client connecting ...")
                //rModel.messageConnecting()
                this.socket = Socket(ip, port)
                
                Log.d(TAG, "client connected")
                this.printWriter = PrintWriter(socket.getOutputStream(), true)

            } catch (e: ConnectException) {
                Log.d(TAG, "connection failed")
            }
        }


    }

//    private fun close() {
//        socket.close()
//        Log.d(TAG, "socket closed")
//
//    }


    fun sendData(data: String) {
        Log.d(TAG, "data to send: $data")
        printWriter?.let {
            val moveStr = data + "\r\n"
            it.println(moveStr)
            Log.d(TAG, "succeeded to send")
        }
    }


}