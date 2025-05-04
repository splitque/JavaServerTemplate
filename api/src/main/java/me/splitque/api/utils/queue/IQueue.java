package me.splitque.api.utils.queue;

public interface IQueue <T> {
    public void put(T object);
    public void delete(T object);
    public boolean equals(Object object);
    public int size();
    public T get();
}
