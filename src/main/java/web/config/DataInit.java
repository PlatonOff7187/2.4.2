package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
@Transactional
public class DataInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        Role role1 = new Role("ADMIN");
        roleService.saveRole(role1);
        Role role2 = new Role("USER");
        roleService.saveRole(role2);
    }

    private void initUsers() {
        User user1 = new User();
        user1.setName("admin");
        user1.setUserName("admin");
        user1.setPassword("admin");
        user1.setEmail("admin@mail.com");
        user1.setAge(25);
        user1.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.save(user1);

        User user2 = new User();
        user2.setName("first");
        user2.setUserName("first");
        user2.setPassword("first");
        user2.setEmail("first@mail.com");
        user2.setAge(30);
        user2.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.save(user2);

    }
}

