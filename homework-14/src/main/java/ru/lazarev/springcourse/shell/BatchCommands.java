package ru.lazarev.springcourse.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {
    public static final String JOB_NAME = "job_name";
    private final JobLauncher jobLauncher;
    private final Job migrationLibraryFromJpaToMongoJob;

    @ShellMethod(value = "startMigrationPostgresToMongoDb", key = "start")
    public void startMigrationJobWithJobOperator() throws Exception {
        JobExecution execution = jobLauncher.run(migrationLibraryFromJpaToMongoJob, new JobParametersBuilder()
            .toJobParameters());
        System.out.println(execution);
    }
}
