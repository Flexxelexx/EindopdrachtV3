package com.example.eindopdrachtbackendv1.security;

import com.example.eindopdrachtbackendv1.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService udService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(udService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeRequests()

                // create user
                .antMatchers(HttpMethod.POST, "/users").permitAll()

                // auth
                .antMatchers(HttpMethod.POST, "/login").permitAll()

                // roles
                .antMatchers(HttpMethod.GET, "/roles/{username}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                // admin
                .antMatchers(HttpMethod.POST, "/users/createadmin").permitAll()

                 // users
                .antMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/users/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/users/{id}/upload/{uploadid}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                //file upload
                .antMatchers(HttpMethod.POST, "/single/uploadDb").permitAll()

                // file download
                .antMatchers(HttpMethod.GET, "/downloadFromDB/**").permitAll()

                // uploads
                .antMatchers(HttpMethod.POST, "/uploads").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/uploads").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/uploads/user/{username}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                // fishingspots
                .antMatchers(HttpMethod.POST, "/fishingspots").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/fishingspots").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                //gears
                .antMatchers(HttpMethod.POST, "/gears").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/gears").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                // delete
                .antMatchers(HttpMethod.DELETE, "/users").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/uploads/{id}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/uploads/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/fishingspots").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/gears").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")


                .antMatchers("/roles").hasAnyAuthority("ADMIN")

                .antMatchers("/**").denyAll()

                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}