package com.example.mqtttest

import android.util.Log
import org.eclipse.paho.client.mqttv3.MqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

object MqttConnector {

    private var client: MqttAsyncClient? = null
    private const val MQTT_URL = "tcp://"
    private const val MQTT_USERNAME = ""
    private const val MQTT_PASSWORD = ""
    private const val QOS = 1

   fun createMqttClient(token: String) {
        if (client != null) {
            return
        }
        try {
            val clientId = MqttAsyncClient.generateClientId()
            val persistence = MemoryPersistence()
            val mqttConnectOptions = MqttConnectOptions()
            mqttConnectOptions.socketFactory = null
            mqttConnectOptions.isCleanSession = true
            mqttConnectOptions.isAutomaticReconnect = true
            mqttConnectOptions.keepAliveInterval = 60
            mqttConnectOptions.userName = MQTT_USERNAME
            mqttConnectOptions.password = MQTT_PASSWORD.toCharArray()
            client = MqttAsyncClient(MQTT_URL, clientId, persistence)


            client!!.setCallback(MqttClientCallback())
            if (!client!!.isConnected) {
                client!!.connect(mqttConnectOptions,token, MqttConnectListener())
            }
        } catch (e: MqttException) {
            Log.e("MqttException", "$e")
        }
    }

    fun subscribe(message: String, topic: String) {
        try {
            client!!.subscribe(topic, QOS, message, MqttSubscribeListener())
            Log.e("Subscribe", "message : $message + topic : $topic")
        } catch (e: MqttException) {
            Log.e("MqttConnector","[MqttManager].subscribe : error = $e")
        }
    }

    fun publish(message: String, topic: String) {
        if (message != null && topic != null){
            val mqttMessage = MqttMessage(message.toByteArray())
            mqttMessage.qos = QOS
            client!!.publish(topic, mqttMessage, null, MqttPublishListener())
            Log.e("Publish", "message : $message + topic : $topic")
        } else {
            Log.e("Publish", "message와 topic을 입력해주세요.")
        }
    }

    fun unsubscribe(topic: String) {
        if (topic != null) {
            client!!.unsubscribe(topic)
            Log.e("unsubscribe", "$topic 구독 취소함")
        } else {
            Log.e("unsubscribe", "Topic을 구독 하세요!!!!!!!")
        }
    }

    fun disconnect() {
        client?.disconnect()
        Log.e("disconnect", "Disconnected from broker")
    }

}