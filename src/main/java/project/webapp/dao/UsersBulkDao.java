package project.webapp.dao;

import org.seasar.doma.*;
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

    @BatchUpdate(batchSize = 10)
    int[] updateBatch(List<Users> users);

    @BatchDelete(batchSize = 10)
    int[] deleteBatch(List<Users> users);

    /**
     * @param users
     * @return affected rows
     */
    @Insert(sqlFile = true)
    int insertBatchWithSQLFile(List<Users> users);
}