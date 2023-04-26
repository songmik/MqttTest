package com.example.mqtttest

import android.util.Log
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttToken

class MqttPublishListener: IMqttActionListener {

    override fun onSuccess(token: IMqttToken?) {
        Log.e("MqttPub Suc", "Publish = ${token?.userContext}")
    }

    override fun onFailure(token: IMqttToken?, exception: Throwable?) {
        Log.e("MqttPub Fai", "Publish Error = $exception")
    }

}