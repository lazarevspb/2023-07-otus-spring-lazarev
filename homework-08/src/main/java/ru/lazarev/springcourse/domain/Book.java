package ru.lazarev.springcourse.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "books")
public class Book {

    @Id
    String id;

    String title;

    @DBRef
    Author author;

    @DBRef
    Genre genre;

    @DBRef
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;
}
