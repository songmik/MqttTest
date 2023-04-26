package com.example.mqtttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mqtttest.databinding.ActivityMainBinding
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.connectButton.setOnClickListener {
            MqttConnector.createMqttClient(binding.messageEditText.text.toString())
        }

        binding.disconnectButton.setOnClickListener {
            MqttConnector.disconnect()
        }

        binding.publishButton.setOnClickListener {
            MqttConnector.publish(binding.messageEditText.text.toString(), binding.topicET.text.toString())
        }

        binding.subscribeButton.setOnClickListener {
            MqttConnector.subscribe(binding.messageEditText.text.toString(), binding.topicET.text.toString())
        }
    }
}

/* 로직(사용 방법)

1. binding.messageEditText.text.toString() 에 message를 입력하고
CONNECT 버튼을 누르면 hi message에 대해 연결이 된다.

2. binding.topicET.text.toString() 에 topic을 입력하고 PUBLISH 버튼을 누르면
입력한 message가 topic 안에 내용으로 담기게 된다.


ex) message = hi / topic = country/hello
-> country/hello = hi 가 입력이 된다.

topic을  SUBSCRIBE하게 되면 topic의 message가 구독이 되는 것.

3. SUBSCRIBE한 뒤 MQTT Explorer에서 같은 Topic을 입력하고 내용(ww)을 적어 PUBLISH 버튼을 눌러주면
MqttClientCallback(MqttClient Arr) 에서 topic + MQTT Explorer에서 적은 내용이 찍힘

 */