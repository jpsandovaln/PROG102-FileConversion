package org.fundacionjala.converter.security;

import org.fundacionjala.converter.controller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final int LEVEL_ENCRYPT = 4;
    private static final String[] RESOURCES = new String[]{
            "/login",
            "/home",
            "/",
            "/createAccount",
            "/user/createUser",
            "/css/**",
            "/user/add",
            "/images/**",
            "/js/**"
    };

    /**
     * every time that the user want to login, this to ask to the table users
     * if exists the user
     * @param auth
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     *filter the http request
     * @param http
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(RESOURCES).permitAll()
                .antMatchers("/admin*").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }

    /**
     * Crypt the password
     * @return bCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder(LEVEL_ENCRYPT);
        return bCryptPasswordEncoder;
    }

    /**
     * Register the service of users and the encrypted of passwords
     * Setting Service to find User in the database.
     * And Setting PassswordEncoder
     * @return bCryptPasswordEncoder
     */
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
