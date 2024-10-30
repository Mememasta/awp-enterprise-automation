package ru.awp.enterprise.automation.config;

import org.eclipse.paho.mqttv5.client.IMqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.client.persist.MqttDefaultFilePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.core.ClientManager;
import org.springframework.integration.mqtt.core.Mqttv5ClientManager;
import org.springframework.integration.mqtt.inbound.Mqttv5PahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.Mqttv5PahoMessageHandler;

@Configuration
public class MqttConfiguration {

//    @Bean
//    public ClientManager<IMqttAsyncClient, MqttConnectionOptions> clientManager() {
//        MqttConnectionOptions connectionOptions = new MqttConnectionOptions();
//        connectionOptions.setServerURIs(new String[]{"tcp://134.0.113.182:1883"});
//        connectionOptions.setConnectionTimeout(30000);
//        connectionOptions.setMaxReconnectDelay(1000);
//        connectionOptions.setAutomaticReconnect(true);
//        connectionOptions.setUserName("passIoT");
//        connectionOptions.setPassword("pass18IoT23".getBytes());
//        Mqttv5ClientManager clientManager = new Mqttv5ClientManager(connectionOptions, "marat/pupa");
//        clientManager.setPersistence(new MqttDefaultFilePersistence());
//        return clientManager;
//    }
//
//    @Bean
//    public IntegrationFlow mqttInFlow(ClientManager<IMqttAsyncClient, MqttConnectionOptions> clientManager) {
//        Mqttv5PahoMessageDrivenChannelAdapter messageProducer =
//                new Mqttv5PahoMessageDrivenChannelAdapter(clientManager, "marat/pupa");
//        messageProducer.setPayloadType(String.class);
//        messageProducer.setManualAcks(true);
//
//        return IntegrationFlow.from(messageProducer)
//                .channel(c -> c.queue("fromMqttChannel"))
//                .get();
//    }
//
//    @Bean
//    public IntegrationFlow mqttOutFlow(ClientManager<IMqttAsyncClient, MqttConnectionOptions> clientManager) {
//        var messageHandler = new Mqttv5PahoMessageHandler(clientManager);
//        messageHandler.setAsync(true);
//        messageHandler.setAsyncEvents(true);
//        return f -> f.handle(messageHandler);
//    }

    @Bean
    public IntegrationFlow mqttInFlow() {
        MqttConnectionOptions connectionOptions = new MqttConnectionOptions();
        connectionOptions.setServerURIs(new String[]{"wss://134.0.113.182:8883"});
        connectionOptions.setConnectionTimeout(30000);
        connectionOptions.setMaxReconnectDelay(1000);
        connectionOptions.setAutomaticReconnect(true);
        connectionOptions.setUserName("passIoT");
        connectionOptions.setPassword("pass18IoT".getBytes());
        return IntegrationFlow.from(
                        new Mqttv5PahoMessageDrivenChannelAdapter(
                                connectionOptions, "subscriber-for-shared-subscription", "marat/pupa"))
                .<byte[], String>transform(String::new)
                .handle(m -> System.out.println("Received message: " + m.getPayload()), e -> e.id("dataHandler"))
                .get();

    }
}
