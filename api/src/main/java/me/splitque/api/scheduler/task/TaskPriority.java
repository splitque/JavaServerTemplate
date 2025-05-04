package me.splitque.api.scheduler.task;

public enum TaskPriority {
    LOW(1),
    NORMAL(2),
    HIGH(3),
    VERY_HIGH(4);

    public final int level;

    TaskPriority(int level) {
        this.level = level;
    }
}
