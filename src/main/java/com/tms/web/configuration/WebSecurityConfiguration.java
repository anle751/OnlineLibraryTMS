package com.tms.web.configuration;

import com.tms.web.services.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/","/login","/register","/home","/images/**/**").permitAll()
                .antMatchers("/library/**/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/userAccount/adminFunctional/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/home")
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/home");
//                .and()
//                .exceptionHandling().accessDeniedHandler(authenticationExceptionHandler());
    }

//    @Bean
//    public AuthenticationExceptionHandler authenticationExceptionHandler(){
//        return new AuthenticationExceptionHandler();
//    }

//    @Bean
//    @Primary
//    public AuthenticationProvider authProvider(){
//        return new WebSecurityAuthProvider();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//        auth.authenticationProvider(authProvider());
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
