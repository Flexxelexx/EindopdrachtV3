package com.example.eindopdrachtbackendv1.Security;

import com.example.eindopdrachtbackendv1.Repositories.UserRepository;
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


                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/auth").permitAll()
                .antMatchers(HttpMethod.PUT, "/auth").permitAll()

                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users").permitAll()
                .antMatchers(HttpMethod.PUT, "/users").permitAll()


                .antMatchers(HttpMethod.POST, "/upload").permitAll()
                .antMatchers(HttpMethod.GET, "/upload").permitAll()
                .antMatchers(HttpMethod.PUT, "/upload").permitAll()

                .antMatchers(HttpMethod.POST, "/fishingspot").permitAll()
                .antMatchers(HttpMethod.GET, "/fishingspot").permitAll()
                .antMatchers(HttpMethod.PUT, "/fishingspot").permitAll()

                .antMatchers(HttpMethod.POST, "/rating").permitAll()
                .antMatchers(HttpMethod.GET, "/rating").permitAll()
                .antMatchers(HttpMethod.PUT, "/rating").permitAll()

                .antMatchers(HttpMethod.POST, "/gear").permitAll()
                .antMatchers(HttpMethod.GET, "/gear").permitAll()
                .antMatchers(HttpMethod.PUT, "/gear").permitAll()

                .antMatchers(HttpMethod.POST, "/location").permitAll()
                .antMatchers(HttpMethod.GET, "/location").permitAll()
                .antMatchers(HttpMethod.PUT, "/location").permitAll()
//                .antMatchers(HttpMethod.POST, "/users").permitAll()
//                .antMatchers(HttpMethod.POST, "/users/create").permitAll()
//                .antMatchers(HttpMethod.POST, "/users/register").permitAll()
//
//                .antMatchers("/**").authenticated()
//
//
//                .antMatchers(HttpMethod.DELETE, "/users").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/uploads").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/fishingspots").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/ratings").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/gears").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/locations").hasRole("ADMIN")
//
//
//                .antMatchers("/admin").hasAuthority("ADMIN")
//                .antMatchers("/secret").hasAuthority("ADMIN")
//
//                .antMatchers("/secret").hasAnyAuthority("ADMIN")
//
//                .antMatchers("/users").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/uploads").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/fishingspots").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/ratings").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/gears").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/locations").hasAnyAuthority("USER", "ADMIN")


                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}


//
//                .antMatchers("/users").authenticated()
//                .antMatchers("/uploads").authenticated()
//                .antMatchers("/fishingspots").authenticated()
//                .antMatchers("/ratings").authenticated()
//                .antMatchers("/gears").authenticated()
//                .antMatchers("/locations").authenticated()
//
//                .antMatchers(HttpMethod.POST, "/auth").permitAll()
//                        .antMatchers(HttpMethod.GET, "/auth").permitAll()
//                        .antMatchers(HttpMethod.PUT, "/auth").permitAll()
//
//                        .antMatchers(HttpMethod.POST, "/users").permitAll()
//                        .antMatchers(HttpMethod.GET, "/users").permitAll()
//                        .antMatchers(HttpMethod.PUT,"/users").permitAll()
//
//
//                .antMatchers(HttpMethod.POST, "/upload").permitAll()
//                        .antMatchers(HttpMethod.GET, "/upload").permitAll()
//                        .antMatchers(HttpMethod.PUT,"/upload").permitAll()
//
//                        .antMatchers(HttpMethod.POST, "/fishingspot").permitAll()
//                        .antMatchers(HttpMethod.GET, "/fishingspot").permitAll()
//                        .antMatchers(HttpMethod.PUT,"/fishingspot").permitAll()
//
//                        .antMatchers(HttpMethod.POST, "/rating").permitAll()
//                        .antMatchers(HttpMethod.GET, "/rating").permitAll()
//                        .antMatchers(HttpMethod.PUT,"/rating").permitAll()
//
//                        .antMatchers(HttpMethod.POST, "/gear").permitAll()
//                        .antMatchers(HttpMethod.GET, "/gear").permitAll()
//                        .antMatchers(HttpMethod.PUT,"/gear").permitAll()
//
//                        .antMatchers(HttpMethod.POST, "/location").permitAll()
//                        .antMatchers(HttpMethod.GET, "/location").permitAll()
//                        .antMatchers(HttpMethod.PUT,"/location").permitAll()