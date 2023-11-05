package project.webapp.dao;

import project.webapp.util.doma.ComponentAndAutowiredDomaConfig;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import project.webapp.entity.Users;

/**
 */
@Dao
@ComponentAndAutowiredDomaConfig
public interface UsersDao {

    /**
     * @param id
     * @return the Users entity
     */
    @Select
    Users selectById(Integer id);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(Users entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(Users entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(Users entity);
}