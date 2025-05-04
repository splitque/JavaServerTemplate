package me.splitque.server;

import me.splitque.api.scheduler.ScheduleThread;
import me.splitque.api.scheduler.task.TaskPriority;

public class Starter {
    public static void main(String[] args) throws InterruptedException {
        ScheduleThread scheduleThread = new ScheduleThread("NEWTHREAD");
        scheduleThread.addTask(() -> {
            System.out.println("Hello, World!");
        }, TaskPriority.NORMAL);
        scheduleThread.startSchedule(0, 3);
    }
}
