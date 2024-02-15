package ru.lazarev.springcourse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.lazarev.springcourse.enums.AuditType;


@Data
@AllArgsConstructor
public class EventKafkaMessage {
    String eventId;
    AuditType eventType;
    AuditEventMessage message;
    Long timestamp;

    @Data
    @AllArgsConstructor
    public static class AuditEventMessage {
        Long userId;
        Long bookId;
    }
}
