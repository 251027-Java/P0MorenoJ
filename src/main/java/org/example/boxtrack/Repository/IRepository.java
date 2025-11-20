package org.example.boxtrack.Repository;

import java.util.List;

public interface IRepository<T> {
    // Create/Insert
    void add(T entity);

    // Update
    void update(T entity);

    // Delete by ID
    void delete(int id);

    // Retrieve one entity by ID
    T getById(int id);

    // Retrieve all entities
    List<T> getAll();
}
