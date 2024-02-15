package ru.lazarev.springcourse.consumer.factory;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Slf4j
public abstract class AbstractKafkaConsumerFactory implements KafkaConsumerFactory {
    private static final String KAFKA_EXCEPTION_MSG = "Exception during consuming message from kafka, exceptionMsg: {}";

    protected void logReceivedMessage(ConsumerRecord<String, ?> consumerRecord) {
        log.info("received key={}, value={} from topic={}, offset={}",
                 consumerRecord.key(),
                 consumerRecord.value(),
                 consumerRecord.topic(),
                 consumerRecord.offset());
    }

    protected void logError(Throwable throwable) {
        log.error(KAFKA_EXCEPTION_MSG, throwable.getMessage());
    }
}
