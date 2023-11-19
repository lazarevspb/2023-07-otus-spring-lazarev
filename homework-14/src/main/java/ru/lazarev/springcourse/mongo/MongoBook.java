package ru.lazarev.springcourse.mongo;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.lazarev.springcourse.postgres.Author;
import ru.lazarev.springcourse.postgres.Genre;

import java.util.List;

@Data
@Document(collection = "mongoBook")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MongoBook {
    @Id
    String id;
    String title;
    List<Author> authors;
    List<Genre> genres;
}
