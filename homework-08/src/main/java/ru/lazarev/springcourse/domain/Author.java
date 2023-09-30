package ru.lazarev.springcourse.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Author {

    @Id
    String id;

    String name;
}