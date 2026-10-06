package cl.ucn.disc.arqsist.library.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class BaseDao<T> {

    protected Dao<T, Integer> dao;

    public BaseDao(ConnectionSource connectionSource, Class<T> clazz) {
        try {
            this.dao = DaoManager.createDao(connectionSource, clazz);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public int create(T entity) {
        try {
            return dao.create(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(T entity) {
        try {
            return dao.update(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(T entity) {
        try {
            return dao.delete(entity);
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