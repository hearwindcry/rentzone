package com.julius.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Collection;

@Data
@Table(name = "t_user")
public class User {

    Long userId;

    @Column(name = "user_name")
    String username;

    String password;

    public static UserDetails ConvertToSecurityUser(User user, Collection<? extends GrantedAuthority > authorities){
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
