package ru.lazarev.springcourse.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import ru.lazarev.springcourse.mongo.MongoBook;
import ru.lazarev.springcourse.postgres.PostgresBook;

import static ru.lazarev.springcourse.config.JobConfig.CHUNK_SIZE;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BookStepConfig {

    private final JobRepository jobRepository;

    private final EntityManagerFactory entityManagerFactory;

    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Step bookStep(ItemReader<PostgresBook> bookReader, ItemWriter<MongoBook> bookWriter,
                         ItemProcessor<PostgresBook, MongoBook> bookItemProcessor) {
        return new StepBuilder("bookStep", jobRepository)
            .<PostgresBook, MongoBook>chunk(CHUNK_SIZE, platformTransactionManager)
            .reader(bookReader)
            .processor(bookItemProcessor)
            .writer(bookWriter)
            .build();
    }

    @Bean
    public JpaCursorItemReader<PostgresBook> bookReader() {
        return new JpaCursorItemReaderBuilder<PostgresBook>()
            .name("bookItemReader")
            .queryString("from PostgresBook")
            .entityManagerFactory(entityManagerFactory)
            .build();
    }

    @Bean
    public ItemProcessor<PostgresBook, MongoBook> bookItemProcessor(BookJpaToMongoConverter converter) {
        return converter::convert;
    }

    @Bean
    public MongoItemWriter<MongoBook> bookWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<MongoBook>()
            .template(mongoTemplate)
            .build();
    }
}
