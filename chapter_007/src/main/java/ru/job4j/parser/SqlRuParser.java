package ru.job4j.parser;

import org.quartz.SchedulerException;

public class SqlRuParser {
    public static void main(String[] args) throws SchedulerException {
        ParserTrigger parserTrigger = new ParserTrigger();
        parserTrigger.start();
    }
}
