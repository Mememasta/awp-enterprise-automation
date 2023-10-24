package ru.awp.enterprise.automation.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import ru.awp.enterprise.automation.models.converter.ValueByAreaMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CustomConversions extends AbstractR2dbcConfiguration {

    private final ObjectMapper objectMapper;

    @Override
    public ConnectionFactory connectionFactory() {
        return null;
    }

    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new CustomListToStringConverter(objectMapper));
        converterList.add(new CustomStringToListConverter(objectMapper));
        return new R2dbcCustomConversions(getStoreConversions(), converterList);
    }

}

@Slf4j
@RequiredArgsConstructor
class CustomListToStringConverter implements Converter<List<ValueByAreaMap>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convert(List<ValueByAreaMap> source) {
        try {
            return objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while serializing list of CustomObject to JSON.", e);
        }
        return "[]";
    }
}

@Slf4j
@RequiredArgsConstructor
class CustomStringToListConverter implements Converter<String, List<ValueByAreaMap>> {

    private final ObjectMapper objectMapper;

    @Override
    public List<ValueByAreaMap> convert(String source) {
        try {
            return Arrays.asList(objectMapper.readValue(source, ValueByAreaMap[].class));
        } catch (JsonProcessingException e) {
            log.error("Error occurred while serializing list of CustomObject to JSON.", e);
        }
        return List.of();
    }
}