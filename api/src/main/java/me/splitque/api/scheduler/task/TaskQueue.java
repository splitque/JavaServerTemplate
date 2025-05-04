package me.splitque.api.scheduler.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskQueue {
    private final List<RegisteredTask> tasksOnQueue;

    public TaskQueue() {
        tasksOnQueue = new ArrayList<>();
    }

    public void get(RegisteredTask task) {
        if (tasksOnQueue.isEmpty()) {
            task.setPosition(1);
            tasksOnQueue.add(task);
            return;
        }

        int posOnQueue = 0;
        for (RegisteredTask registeredTask : tasksOnQueue) {
            if (registeredTask.getPriority().level < task.getPriority().level) {
                posOnQueue = registeredTask.getPosition();
                break;
            }
        }

        for (RegisteredTask registeredTask : tasksOnQueue) {
            if (registeredTask.getPosition() <= posOnQueue) registeredTask.setPosition(registeredTask.getPosition() + 1);
        }

        task.setPosition(posOnQueue);
        tasksOnQueue.add(task);
    }

    public Task getTask() {
        RegisteredTask task = null;
        for (RegisteredTask registeredTask : tasksOnQueue) {
            if (registeredTask.getPosition() == 1) task = registeredTask;
        }

        tasksOnQueue.remove(task);

        for (RegisteredTask registeredTask : tasksOnQueue) {
            registeredTask.setPosition(registeredTask.getPosition() - 1);
        }

        return task.getTask();
    }
}
