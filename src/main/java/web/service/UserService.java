package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> index();
    User show(int id);
    void save(User user);
    User update(User updateUser);
    void delete(int id);
    User getUserByName(String userName);
}
