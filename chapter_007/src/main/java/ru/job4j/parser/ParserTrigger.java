package ru.job4j.parser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class ParserTrigger {
    private String cron;

    public ParserTrigger() {
        cron = new Config().get("cron.time");
    }

    public void start() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(ParserJob.class).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}
