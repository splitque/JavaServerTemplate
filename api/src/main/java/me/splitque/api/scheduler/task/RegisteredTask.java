package me.splitque.api.scheduler.task;

public class RegisteredTask {
    private Task task;
    private TaskPriority priority;
    private int position;

    public RegisteredTask(Task task, TaskPriority priority) {
        this.task = task;
        this.priority = priority;
    }

    public Task getTask() {
        return task;
    }
    public TaskPriority getPriority() {
        return priority;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
}
