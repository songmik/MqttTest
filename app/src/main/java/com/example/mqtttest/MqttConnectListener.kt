package com.example.mqtttest

import android.util.Log
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttToken

class MqttConnectListener: IMqttActionListener {

    override fun onSuccess(token: IMqttToken) {
        Log.e("MqttConnect Suc", "Connect : success to connect = ${token.userContext}")
    }

    override fun onFailure(token: IMqttToken, exception: Throwable) {
        Log.e("MqttConnect Fa", "fail : success to connect = ${token.userContext}, , error = $exception")
    }
}