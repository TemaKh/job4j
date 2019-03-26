package ru.job4j.parser;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Class defines the task for the Quartz framework scheduler.
 */
public class ParserJob implements Job {
    private final Parser parser = new Parser();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        parser.parsing();
    }
}
