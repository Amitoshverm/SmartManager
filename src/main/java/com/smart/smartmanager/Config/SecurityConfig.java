package com.smart.smartmanager.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.smart.smartmanager.Services.SecurityCustomUserDetailService;


// Our user detail service which manages user details 
@Configuration
public class SecurityConfig {




    // create user and login using code within in db service 
    // @Bean
    // public UserDetailsService userDetailsService() {

    //     UserDetails user = User
    //     .withUsername("admin123")
    //     .password("admin123")
    //     .roles( "USER")
    //     .build();


    //     var inMemoryUserDetailsManager =  new InMemoryUserDetailsManager(user);
    //     return inMemoryUserDetailsManager;
    // }
    private OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;
    
    private SecurityCustomUserDetailService customUserDetailService;

    public SecurityConfig(SecurityCustomUserDetailService customUserDetailService, OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler) {
        this.customUserDetailService = customUserDetailService;
        this.oAuthAuthenticationSuccessHandler = oAuthAuthenticationSuccessHandler;
    }

    

    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // user detail services object:
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        // password encoders object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        //configure urls which are supposed to be public and which will be private 
        httpSecurity.authorizeHttpRequests(authorize->{
            // authorize.requestMatchers("/home", "/register", "/login", "/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login");
            // now default login page is replaced by our custom login page
            // and this login will process at 
            formLogin.loginProcessingUrl("/authenticate");
            // this will be forwarded to ->
            formLogin.successForwardUrl("/user/profile");
            // or if thrown error 
            // formLogin.failureForwardUrl("login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
        });


        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        
        
        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });


        // oauth configurations
        httpSecurity.oauth2Login((OAuth2LoginConfigurer<HttpSecurity> oauth) -> {
            oauth.loginPage("/login");
            oauth.successHandler(oAuthAuthenticationSuccessHandler);

        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
