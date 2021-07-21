package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    User getUserByName(String userName);

    List<User> index();
    User show(int id);
    void save(User user);
    User update(User updateUser);
    void delete(int id);
}
