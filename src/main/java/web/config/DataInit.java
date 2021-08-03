package web.config;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataInit {
   private final UserService userService;
   private final RoleService roleService;

   @PostConstruct
    public void init() {
       initRoles();
       initUsers();
   }
   private void initRoles() {
       Role role1 = new Role();
       role1.setUserName("ADMIN");
       roleService.saveRole(role1);
       Role role2 = new Role();
       role2.setUserName("USER");
       roleService.saveRole(role2);
       Role role3 = new Role();
       role3.setUserName("USER");
       roleService.saveRole(role3);
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


       User user3 = new User();
       user3.setName("second");
       user3.setUserName("second");
       user3.setPassword("second");
       user3.setEmail("second@mail.com");
       user3.setAge(35);
       user3.setRoles(Set.of(roleService.getRoleByName("USER")));
       userService.save(user3);
   }
}

