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

                // registreren en login vrij
                .antMatchers(HttpMethod.POST, "/register/**").permitAll()

                .antMatchers(HttpMethod.POST, "/login/**").permitAll()
                .antMatchers(HttpMethod.GET, "/login/**").permitAll()

                // auth
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .antMatchers(HttpMethod.GET,"/auth/**").permitAll()

                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/login/**").permitAll()
//                .antMatchers(HttpMethod.POST,"/uploads/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/uploads/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/fishingspots").permitAll()
//                .antMatchers(HttpMethod.GET,"/fishingspots/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/single/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/downloadFromDB/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/download/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/photos/**").permitAll()
//
//                .antMatchers("/**").authenticated()
//
//
//                .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
//
//
//                .antMatchers("/admin").hasAuthority("ADMIN")
//                .antMatchers("/secret").hasAuthority("ADMIN")
//
//
//                .antMatchers("/users/**").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/uploads/**").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/fishingspots/**").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/ratings/**").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/gears/**").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/locations/**").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/single/**").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/download/**").hasAnyAuthority("USER", "ADMIN")


                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}