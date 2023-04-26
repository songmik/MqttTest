package com.example.mqtttest

import android.util.Log
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage

class MqttClientCallback : MqttCallbackExtended {

    // Mqtt 종료 시 호출 되는 함수
    override fun connectionLost(cause: Throwable?) {
        Log.e("MqttClient Lost", "Mqtt Closed = $cause")
    }

    // 구독된 메세지가 전달 받는 메세지
    override fun messageArrived(topic: String?, message: MqttMessage?) {
        Log.e("MqttClient Arr", "$topic ${message?.payload?.toString(Charsets.UTF_8)}")
    }

    override fun deliveryComplete(token: IMqttDeliveryToken?) {
        Log.e("MqttClient Com", "$token")
    }

    // Mqtt 연결 후 호출 되는 함수
    override fun connectComplete(reconnect: Boolean, serverURI: String?) {
        Log.e("MqttClient Con", "$reconnect")
    }

}