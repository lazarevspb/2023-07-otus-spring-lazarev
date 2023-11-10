package ru.lazarev.springcourse.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Properties;
import java.util.UUID;


@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {

    public static final String JOB_NAME = "job_name";
    private final JobOperator jobOperator;

    private final JobExplorer jobExplorer;

    @ShellMethod(value = "startMigrationJobWithJobOperator", key = "start")
    public void startMigrationJobWithJobOperator() throws Exception {
        Properties properties = new Properties();
        properties.put("4restart", UUID.randomUUID().toString());
        Long executionId = jobOperator.start(JOB_NAME, properties);
        System.out.println(jobOperator.getSummary(executionId));
    }

    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
        System.out.println(jobExplorer.getLastJobInstance(JOB_NAME));
    }
}
