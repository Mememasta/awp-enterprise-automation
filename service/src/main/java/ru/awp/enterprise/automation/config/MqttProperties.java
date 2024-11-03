package ru.awp.enterprise.automation.config;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "mqtt.broker")
public class MqttProperties extends MqttConnectOptions {}
