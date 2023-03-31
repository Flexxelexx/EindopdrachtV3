package com.example.eindopdrachtbackendv1.security;

import com.example.eindopdrachtbackendv1.repositories.UserRepository;
import com.example.eindopdrachtbackendv1.models.Role;
import com.example.eindopdrachtbackendv1.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepos;

    public MyUserDetailsService(UserRepository repos) {
        this.userRepos = repos;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> ou = userRepos.findByUsername(username);
        if (ou.isPresent()) {
            User user = ou.get();
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role role : user.getRoles()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
            }


            return new org
                    .springframework
                    .security
                    .core
                    .userdetails
                    .User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
