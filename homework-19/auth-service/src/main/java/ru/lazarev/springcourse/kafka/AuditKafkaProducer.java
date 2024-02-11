package ru.lazarev.springcourse.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.kafka.message.AuditKafkaMessage;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditKafkaProducer {

    @Value("${spring.kafka.topics.audit}")
    private String deviceChangedTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper mapper;

    public void publish(AuditKafkaMessage message) {
        if (Objects.nonNull(message.getMessage().getUserId())) {
            String stringMessage = null;
            try {
                stringMessage = mapper.writeValueAsString(message);
            } catch (JsonProcessingException e) {
                log.error("Failed to send AuditKafkaMessage in kafka, message: {}", message);
            }
            kafkaTemplate.send(deviceChangedTopic, message.getEventId(), stringMessage);
            log.info("Publish AuditKafkaMessage in kafka: {}", stringMessage);
        } else {
            log.warn("AuditKafkaMessage will not be sent to Kafka, userId is null");
        }
    }
}
