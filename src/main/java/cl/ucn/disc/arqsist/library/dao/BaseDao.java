/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;

import cl.ucn.disc.arqsist.library.model.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;


public abstract class BaseDao<T> {

    protected final Dao<T, Integer> dao;

  
    protected BaseDao(ConnectionSource connectionSource, Class<T> clazz) {
        try {
            this.dao = DaoManager.createDao(connectionSource, clazz);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

  
    public BaseDao(ConnectionSource connectionSource, Class<Book> class1) {
        //TODO Auto-generated constructor stub
    }


    public List<T> findAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

 
    public T findById(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public void create(T entity) {
        try {
            dao.create(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

  
    public void update(T entity) {
        try {
            dao.update(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(T entity) {
        try {
            dao.delete(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <R> R transaction(Callable<R> callable) throws SQLException {
        try {
            return TransactionManager.callInTransaction(dao.getConnectionSource(), callable);
        } catch (SQLException e) {
            if (e.getCause() instanceof RuntimeException cause) {
                throw cause;
            }
            throw e;
        }
    }
}