package ru.awp.enterprise.automation.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.integration.mqtt.event.MqttIntegrationEvent;
import org.springframework.integration.mqtt.event.MqttMessageSentEvent;
import org.springframework.stereotype.Component;

@Component
public class MqttListener implements ApplicationListener<MqttIntegrationEvent> {
    @Override
    public void onApplicationEvent(MqttIntegrationEvent event) {
        System.out.println(event);
    }
}
