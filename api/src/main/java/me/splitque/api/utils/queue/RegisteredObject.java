package me.splitque.api.utils.queue;

public class RegisteredObject<T> {
    private final T object;
    private int position;

    public RegisteredObject(T object, int position) {
        this.object = object;
        this.position = position;
    }

    public T getObject() {
        return object;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
}