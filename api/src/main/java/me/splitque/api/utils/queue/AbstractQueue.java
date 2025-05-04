package me.splitque.api.utils.queue;

public abstract class AbstractQueue<T> implements IQueue<T> {
    private final boolean isCyclic;

    public AbstractQueue(boolean cyclic) {
        isCyclic = cyclic;
    }

    public boolean getCyclic() {
        return isCyclic;
    }
}
