package ru.lazarev.springcourse.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobConfig {
    public static final int CHUNK_SIZE = 5;

    public static final String JOB_NAME = "job_name";

    private final JobRepository jobRepository;

    @Bean
    public Job migrationLibraryFromJpaToMongoJob(Step bookStep) {
        return new JobBuilder(JOB_NAME, jobRepository)
            .incrementer(new RunIdIncrementer())
            .flow(bookStep)
            .end()
            .listener(new JobExecutionListener() {
                @Override
                public void beforeJob(@NonNull JobExecution jobExecution) {
                    log.info("Начало миграции библиотеки");
                }

                @Override
                public void afterJob(@NonNull JobExecution jobExecution) {
                    log.info("Конец миграции библиотеки");
                }
            })
            .build();
    }
}
