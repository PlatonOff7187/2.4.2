package web.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;


@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(nullable = false, name = "name")
    @NotEmpty(message = "The name cannot be empty")
    @Size(min = 2, max = 15, message = "The name must be more than 2 and less than 15 characters")
    private String name;
    @Column(nullable = false, name = "username")
    @NotEmpty(message = "The lastname cannot be empty")
    @Size(min = 2, max = 15, message = "The lastname must be more than 2 and less than 15 characters")
    private String userName;
    @Column(nullable = false, name = "age")
    @Min(value = 0, message = "The age must be more than 18 years old")
    private int age;
    @Column(nullable = false, unique = true, name = "email")
    @NotEmpty(message = "The email cannot be empty")
    @Email(message = "invalid email format")
    private String email;
    @Column(name = "password")
    @NotEmpty(message = "Password dont Empty")
    private String password;

    public User(int id, String userName, String password, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}