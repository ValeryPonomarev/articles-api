package com.lordpvi.articles.persistence.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordpvi.articles.infrastructure.error.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class KafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private JsonSerializer<Object> jsonSerializer;
    private final ObjectMapper objectMapper;

    @Autowired
    private void setJsonSerializer(JsonSerializer<Object> jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    public void publish(String topic, KafkaEvent<?> kafkaEvent) {
        try {
            kafkaTemplate.send(topic, serializeEvent(topic, kafkaEvent));
        } catch (Exception e) {
            log.error("Failed publish event. Topic: {}, Event: {}", topic, kafkaEvent, e);
            throw new AppException(e);
        }
    }

    private String serializeEvent(String topic, Object data) {
        return data instanceof String ? (String) data : new String(jsonSerializer.serialize(topic, data), Charset.defaultCharset());
    }

}
