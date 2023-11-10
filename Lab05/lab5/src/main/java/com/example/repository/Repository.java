package com.example.repository;

import java.util.List;

public interface Repository<T> {
    public boolean add(T t);

    public T get(int id);

    public List<T> getAll();

    public boolean remove(int id);

    public boolean remove(T t);


}