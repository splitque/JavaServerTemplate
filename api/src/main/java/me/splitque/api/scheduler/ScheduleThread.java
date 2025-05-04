package me.splitque.api.scheduler;

import me.splitque.api.scheduler.task.RegisteredTask;
import me.splitque.api.scheduler.task.Task;
import me.splitque.api.scheduler.task.TaskPriority;
import me.splitque.api.scheduler.task.TaskQueue;

public class ScheduleThread extends Thread {
    private TaskQueue taskQueue;

    public ScheduleThread(String name) {
        super(name);
        taskQueue = new TaskQueue();
    }

    public void addTask(Task task, TaskPriority priority) {
        taskQueue.get(new RegisteredTask(task, priority));
    }

    public void startSchedule(int delay, int period) throws InterruptedException {
        if (delay != 0) sleep(delay * 100);
        start();

        while (true) {
            sleep(period * 100);
            run();
        }
    }

    @Override
    public void run() {
        taskQueue.getTask().onTask();
    }
}
