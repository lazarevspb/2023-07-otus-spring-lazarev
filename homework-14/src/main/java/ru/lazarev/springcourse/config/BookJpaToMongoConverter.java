package ru.lazarev.springcourse.config;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.mongo.MongoBook;
import ru.lazarev.springcourse.postgres.PostgresBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BookJpaToMongoConverter implements Converter<PostgresBook, MongoBook> {

    private final Map<Long, MongoBook> cache = new HashMap<>();

    @Override
    public MongoBook convert(PostgresBook source) {
        if (cache.containsKey(source.getId())) {
            return cache.get(source.getId());
        }
        MongoBook target = new MongoBook();
        target.setTitle(source.getTitle());
        target.setGenres(List.of(source.getGenre()));
        target.setAuthors(List.of(source.getAuthor()));
        target.setId(new ObjectId().toString());
        cache.put(source.getId(), target);
        return target;
    }
}