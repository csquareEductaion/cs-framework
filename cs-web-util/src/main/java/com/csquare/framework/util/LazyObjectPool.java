package com.csquare.framework.util;

import java.util.concurrent.ConcurrentLinkedQueue;


public abstract class LazyObjectPool<T> {

    private ConcurrentLinkedQueue<T> pool = new ConcurrentLinkedQueue<T>();

    private String name;
    private int maxIdleObjects;

    public LazyObjectPool(final String name, final int maxIdleObjects) {
        this.name = name;
        this.maxIdleObjects = maxIdleObjects;
    }

    public T borrowObject() {

        T object = pool.poll();
        if (object == null) {
            object = createObject(name);
            pool.add(object);
            return object;
        }

        int size = pool.size();
        if (size <= maxIdleObjects) {
            return object;
        }

        int sizeToBeRemoved = size - maxIdleObjects;
        for (int i = 0; i < sizeToBeRemoved; i++) {
            T objectToClose = pool.poll();
            destroyObject(objectToClose);
        }

        return object;
    }

    /*
     * Returns object back to the pool.
     * 
     * @param object object to be returned
     */
    public void returnObject(T object, boolean closed) {

        if (object == null) {
            return;
        }

        if (closed) {
            destroyObject(object);
            return;
        }

        int size = pool.size();
        if (size < maxIdleObjects) {
            this.pool.offer(object);
            return;
        }

        int sizeToBeRemoved = size - maxIdleObjects;
        for (int i = 0; i < sizeToBeRemoved; i++) {
            T objectToClose = pool.poll();
            destroyObject(objectToClose);
        }
    }

    public void shutdown() {

        int size = pool.size();
        for (int i = 0; i < size; i++) {
            T objectToClose = pool.poll();
            destroyObject(objectToClose);
        }

        pool.clear();
    }

    /*
     * Creates a new object.
     * 
     * @return T new object
     */
    protected abstract T createObject(String name);

    /*
     * Creates a new object.
     * 
     * @return T new object
     */
    protected abstract void destroyObject(T object);
}