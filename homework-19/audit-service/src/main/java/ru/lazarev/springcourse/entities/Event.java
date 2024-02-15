package ru.lazarev.springcourse.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.lazarev.springcourse.enums.AuditType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Data
@Table(name = "events")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "book_id")
    Long bookId;

    @Column(name = "timestamp")
    Instant timestamp;

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    AuditType operationType;

}
