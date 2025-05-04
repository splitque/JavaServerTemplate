package me.splitque.api.utils.queue;

public class QueueList<T> extends AbstractQueue<T> {
    private RegisteredObject<T>[] objectsOnQueue;

    public QueueList(boolean cyclic) {
        super(cyclic);
    }

    @Override
    public void put(T object) {
        if (objectsOnQueue.length == 0) {
            putObject(object, 0);
            return;
        }

        boolean checked = false;
        int position = 0;
        for (int i = 0; !checked; i++) {
            for (RegisteredObject<T> regObj : objectsOnQueue) {
                if (regObj.getPosition() == i) break;
                position = i;
                checked = true;
            }
        }
        putObject(object, position);
    }

    @Override
    public void delete(T object) {
        deleteObject(getRegisteredObject(object));
    }

    @Override
    public boolean equals(Object object) {
        for (RegisteredObject<T> registeredObject : objectsOnQueue) {
            if (object == registeredObject) return true;
        }
        return false;
    }

    @Override
    public int size() {
        boolean getted = false;
        int size = 0;

        for (int i = 0; !getted; i++) {
            for (RegisteredObject<T> registeredObject : objectsOnQueue) {
                if (registeredObject.getPosition() == i) break;
                size = i;
                getted = true;
            }
        }

        return size--;
    }

    @Override
    public T get() {
        return getCyclic() ? cyclicGet() : removalGet();
    }
    
    private void putObject(T object, int position) {
        objectsOnQueue[objectsOnQueue.length + 1] = new RegisteredObject<T>(object, position);
    }

    private RegisteredObject<T> getRegisteredObject(T object) {
        for (RegisteredObject<T> registeredObject : objectsOnQueue) {
            if (registeredObject.getObject() == object) return registeredObject;
        }
        return null;
    }

    private void deleteObject(RegisteredObject<T> object) {
        boolean deleted = false;
        int pos = 0;
        for (int i = 0; i < objectsOnQueue.length; i++) {
            if (!deleted) {
                if (objectsOnQueue[i] == object) {
                    objectsOnQueue[i] = null;
                    pos = i;
                    deleted = true;
                    continue;
                }
            }

            RegisteredObject<T> regObj = objectsOnQueue[i];
            objectsOnQueue[i] = null;
            objectsOnQueue[pos] = regObj;
            pos = i;
        }
    }

    private T cyclicGet() {
        RegisteredObject<T> object = null;

        for (RegisteredObject<T> registeredObject : objectsOnQueue) {
            if (registeredObject.getPosition() == 0) {
                registeredObject.setPosition(size());
                object = registeredObject;
            }
        }
        for (RegisteredObject<T> registeredObject : objectsOnQueue) {
            if (registeredObject != object) registeredObject.setPosition(registeredObject.getPosition() - 1);
        }

        if (object == null) return null;
        else return object.getObject();
    }
    private T removalGet() {
        RegisteredObject<T> object = null;

        for (RegisteredObject<T> registeredObject : objectsOnQueue) {
            if (registeredObject.getPosition() == 0) object = registeredObject;
        }

        deleteObject(object);

        for (RegisteredObject<T> registeredObject : objectsOnQueue) {
            registeredObject.setPosition(registeredObject.getPosition() - 1);
        }

        if (object == null) return null;
        else return object.getObject();
    }
}