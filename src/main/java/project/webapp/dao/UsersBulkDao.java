package project.webapp.dao;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import project.webapp.entity.Users;
import project.webapp.util.doma.ComponentAndAutowiredDomaConfig;

import java.util.List;

/**
 * 自前で作成したもの
 */
@Dao
@ComponentAndAutowiredDomaConfig
public interface UsersBulkDao {

    /**
     * @param users
     * @return affected rows
     */
    @BatchInsert(batchSize = 10)
    int[] insertBatch(List<Users> users);


    /**
     * @param users
     * @return affected rows
     */
    @Insert(sqlFile = true)
    int insertBatchWithSQLFile(List<Users> users);
}