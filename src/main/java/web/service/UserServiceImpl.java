package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDaoImpl;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDaoImpl roleDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(){

    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getUserName())).collect(Collectors.toList());
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }


    public User findByUsername(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }



    @Override
    @Transactional
    public List<User> index() {
        return userDao.index();
    }

    @Override
    @Transactional
    public User show(int id) {
        return userDao.show(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getRoleById(1));
        user.setRoles(roles);
        userDao.save(user);

    }

    @Override
    @Transactional
    public User update(User updateUser) {
        Set<Role> roles = new HashSet<>();
        if(updateUser.getId() == 1) {
            roles.add(roleDao.getRoleById(1));
        } else {
            roles.add(roleDao.getRoleById(2));
        }
        updateUser.setRoles(roles);
        return userDao.update(updateUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

}
