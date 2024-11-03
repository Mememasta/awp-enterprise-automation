package ru.awp.enterprise.automation.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import ru.awp.enterprise.automation.listener.MqttHandler;
import ru.awp.enterprise.automation.service.MachineDataService;

@Configuration
@EnableConfigurationProperties(value = MqttProperties.class)
public class MqttConfiguration {


    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MqttPahoClientFactory clientFactory(MqttProperties mqttProperties) {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(mqttProperties);
        return factory;
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inbound(MqttProperties mqttProperties) {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("awp-enterprise-automation", clientFactory(mqttProperties));
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setOutputChannel(mqttInputChannel());
        adapter.setCompletionTimeout(5000);
        adapter.setQos(0);
        adapter.addTopic("marat/pupa");
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler(MachineDataService machineDataService) {
        return new MqttHandler(machineDataService);
    }
}
