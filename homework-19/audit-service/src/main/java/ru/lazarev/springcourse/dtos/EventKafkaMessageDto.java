package ru.lazarev.springcourse.dtos;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.lazarev.springcourse.enums.AuditType;


@Value
@Builder
@Jacksonized
public class EventKafkaMessageDto {
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
