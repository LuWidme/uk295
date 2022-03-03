package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//EnableWebSecurity activates the security filters for web pages and sets this class as the configuraion class.
// Per default, every endpoint in the project requires a login
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // in memory authentication
    auth.inMemoryAuthentication()
            .withUser("test")
            .password("test")
            .roles("USER")
//                can chain multiple objects
            .and()
            .withUser("test2")
            .password("test2")
            .roles("ADMIN");
}

//    defines password encoding strategy
    @Bean
    public PasswordEncoder getPasswordEncoder() {
//        this "encoder" uses the "no encoding" strategy, i.e. plain text. ONLY FOR TESTING!
        return NoOpPasswordEncoder.getInstance();
    }

    //Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                check form top to bottom => start with most restrictive

                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/user","/api/products/**").authenticated()
                .antMatchers("/api").permitAll()
//                all paths in current levels and below
//                .antMatchers("/**")
//                default login
                .and().formLogin();
    }
}


