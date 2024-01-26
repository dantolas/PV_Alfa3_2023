package com.kuta.db.DAO.DAOs;

import java.util.List;

/**
 * Interface to implement the Database Access Object architecture.
 * It should cover all basic CRUD operations.
 * 
 */
public interface DAO<T> {

    /**
     * Get a row by it's UUID.
     * Every table in the DB has a UUID.
     * @param id
     * @return
     */
    T getByUUID(String id);
    
    /**
     * Get all rows in DB.
     * @return - List of T object.
     */
    List<T> getAll();
    
    /**
     * Insert record to the db.
     * Also returns boolean stating success or failure.
     * @param t
     * @return - True if success, false if exception.
     */
    boolean insert(T t);
    
    /**
     * Update row in db.
     * Also returns boolean stating success or failure.
     * @param t
     * @return - True if success, false if exception.
     */
    boolean update(T t);
    
    /**
     * Delete row from db.
     *Also returns boolean stating success or failure.
     * @param t
     * @return - True if success, false if exception.
     */
    boolean delete(T t);
    
}
