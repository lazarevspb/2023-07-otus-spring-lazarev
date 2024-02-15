package ru.lazarev.springcourse.library.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.library.dto.ContentDto;
import ru.lazarev.springcourse.library.enums.AuditType;
import ru.lazarev.springcourse.library.feign.StorageServiceProxy;
import ru.lazarev.springcourse.library.kafka.AuditKafkaProducer;
import ru.lazarev.springcourse.library.kafka.message.AuditKafkaMessage;
import ru.lazarev.springcourse.library.service.ContentService;

import java.time.Instant;
import java.util.UUID;

import static ru.lazarev.springcourse.library.enums.AuditType.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContentServiceImpl implements ContentService {
    StorageServiceProxy storageServiceProxy;
    AuditKafkaProducer auditKafkaProducer;
    @Override public ContentDto getContent(Long bookId, Long userId) {
        var content = storageServiceProxy.getContent(bookId);
        auditKafkaProducer.publish(getAuditKafkaMessage(userId, bookId, GET_BOOK_CONTENT));
        return content;
        
    }

    private AuditKafkaMessage getAuditKafkaMessage(Long userId, Long bookId, AuditType eventType) {
        return AuditKafkaMessage.builder()
            .eventId(UUID.randomUUID().toString())
            .eventType(eventType)
            .timestamp(Instant.now().toEpochMilli())
            .message(AuditKafkaMessage.AuditEventMessage.builder()
                         .userId(userId)
                         .bookId(bookId)
                         .build()).build();
    }
}
