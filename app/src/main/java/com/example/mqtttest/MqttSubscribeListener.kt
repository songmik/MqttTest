package com.example.mqtttest

import android.util.Log
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttToken

class MqttSubscribeListener: IMqttActionListener {

    override fun onSuccess(token: IMqttToken) {
        Log.e("MqttSub Suc", "Subscribe = ${token.userContext}")
    }

    override fun onFailure(token: IMqttToken, t: Throwable) {
        Log.e("MqttSub Fai", "Subscribe Error = $t")
    }
}