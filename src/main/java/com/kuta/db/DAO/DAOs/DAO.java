package com.kuta.db.DAO.DAOs;

import java.util.List;

/**
 * DAO
 */
public interface DAO<T> {

    T getById(String id);
    
    List<T> getAll();
    
    boolean save(T t);
    
    boolean update(T t, String[] params);
    
    boolean delete(T t);
    
}
