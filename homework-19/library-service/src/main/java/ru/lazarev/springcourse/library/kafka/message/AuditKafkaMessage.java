package ru.lazarev.springcourse.library.kafka.message;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.lazarev.springcourse.library.enums.AuditType;

@Value
@Builder
@Jacksonized
public class AuditKafkaMessage {
    String eventId;
    AuditType eventType;
    AuditEventMessage message;
    Long timestamp;

    @Value
    @Builder
    @Jacksonized
    public static class AuditEventMessage {
        Long userId;
        Long bookId;
    }

}
