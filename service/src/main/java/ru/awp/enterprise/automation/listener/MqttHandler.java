package ru.awp.enterprise.automation.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import ru.awp.enterprise.automation.service.MachineDataService;

@RequiredArgsConstructor
public class MqttHandler implements MessageHandler {

    private final MachineDataService machineDataService;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String topic = (String) message.getHeaders().get("mqtt_receivedTopic");
        double value = Double.parseDouble((String) message.getPayload());
        if (value == -127D) {
            return;
        }
        System.out.println("TOPIC: " + topic + ", MESSAGE: " + value);
        machineDataService.save(topic, String.valueOf(value)).subscribe();
    }
}
