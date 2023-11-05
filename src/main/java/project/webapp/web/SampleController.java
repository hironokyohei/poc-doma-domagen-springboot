package project.webapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import project.webapp.dao.UsersBulkDao;
import project.webapp.dao.UsersDao;
import project.webapp.entity.Users;

import java.util.Arrays;

// http://localhost:8080/sample?identityId=hirono_1
@Controller
@RequestMapping("/sample")
public class SampleController
{
    @Autowired
    private final UsersDao usersDao;

    @Autowired
    private final UsersBulkDao usersBulkDao;

    public SampleController() {
        usersDao = null;
        usersBulkDao = null;
    }

    @Autowired
    public SampleController(UsersDao usersDao, UsersBulkDao usersBulkDao){
        this.usersDao = usersDao;
        this.usersBulkDao = usersBulkDao;
    }

    @RequestMapping
    public String index(int id, Model model) {
        Users userInfo = usersDao.selectById(id);
        model.addAttribute("user", userInfo);
        return "sample/sample";
    }

    // http://localhost:8080/sample/insert?identityId=hirono_1
    @RequestMapping("/insert")
    public String insert(int id,Model model) {
        Users user = new Users();
        user.setId(id);
        user.setName("dummy_name" + "_" + id);
        user.setAddress("dummy_address" + "_" + id);

        usersDao.insert(user);

        model.addAttribute("user", user);
        return "sample/insert_complete";
    }

    // http://localhost:8080/sample/update?identityId=hirono_1&updatePointToken=BBB
    @RequestMapping("/update")
    public String update(int id, String updateName, Model model) {
        Users user = usersDao.selectById(id);
        user.setName(updateName);

        usersDao.update(user);
        model.addAttribute("user", user);
        return "sample/update_complete";
    }

    // http://localhost:8080/sample/delete?identityId=hirono_1
    @RequestMapping("/delete")
    public String update(int id, Model model) {
        Users user = usersDao.selectById(id);
        usersDao.delete(user);
        model.addAttribute("user", user);
        return "sample/delete_complete";
    }

    // http://localhost:8080/sample/batchInsert?id1=3&id2=4
    @RequestMapping("/batchInsert")
    public String batchInsert(int id1, int id2, Model model) {
        Users user1 = new Users();
        user1.setId(id1);
        user1.setName("dummy_name" + "_" + id1);
        user1.setAddress("dummy_address" + "_" + id1);

        Users user2 = new Users();
        user2.setId(id2);
        user2.setName("dummy_name" + "_" + id2);
        user2.setAddress("dummy_address" + "_" + id2);

        usersDao.delete(user1);
        usersDao.delete(user2);

        usersBulkDao.insertBatch(Arrays.asList(user1, user2));
        model.addAttribute("user1", user1);
        model.addAttribute("user2", user2);

        return "sample/bulk_insert_complete";
    }

    // http://localhost:8080/sample/bulkInsert?identityId1=hirono_10&identityId2=hirono_11
    @RequestMapping("/batchInsertWithSQLFile")
    public String batchInsertWithSQLFile(int id1, int id2, Model model) {
        Users user1 = new Users();
        user1.setId(id1);
        user1.setName("dummy_name" + "_" + id1);
        user1.setAddress("dummy_address" + "_" + id1);

        Users user2 = new Users();
        user2.setId(id2);
        user2.setName("dummy_name" + "_" + id2);
        user2.setAddress("dummy_address" + "_" + id2);

        usersDao.delete(user1);
        usersDao.delete(user2);

        usersBulkDao.insertBatchWithSQLFile(Arrays.asList(user1, user2));
        model.addAttribute("user1", user1);
        model.addAttribute("user2", user2);

        return "sample/bulk_insert_complete";
    }
}

