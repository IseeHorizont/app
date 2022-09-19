package com.edu.ulab.app.storage;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();
    T getById(Long id);
    T create(T t);
    T update(T t);
    T deleteById(Long id);
}
