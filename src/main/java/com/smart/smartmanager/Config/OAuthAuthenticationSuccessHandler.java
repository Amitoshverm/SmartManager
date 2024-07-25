package com.smart.smartmanager.Config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.smart.smartmanager.Entity.Providers;
import com.smart.smartmanager.Entity.User;
import com.smart.smartmanager.Repositories.UserRepository;
import com.smart.smartmanager.helper.AppConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    private UserRepository userRepository;
    public OAuthAuthenticationSuccessHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenticationSuccessHandler");


        // we have to identify the provider, is it google, github or linkedin before saving to db, so first lets identify the provider
        var oauth2AuthenticationTokken = (OAuth2AuthenticationToken)authentication;

        //here we will get which is our provider 
        String authorizedClientRegistrationId = oauth2AuthenticationTokken.getAuthorizedClientRegistrationId().toString();

        logger.info(authorizedClientRegistrationId);

        var oauthUser =(DefaultOAuth2User)authentication.getPrincipal();

        oauthUser.getAttributes().forEach((key, value) -> {
            logger.info(key+" : "+value);
        });

        //set default values to use like id, name, email, and more which are needed
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRolesList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);




        //and now we will check which is our provider and knowing it, we'll do its implementation
        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
            // if login type is google 
            user.setEmail(oauthUser.getAttribute("email").toString());
            user.setProfilePicture(oauthUser.getAttribute("picture").toString());
            user.setName(oauthUser.getAttribute("name").toString());
            user.setProviderUserId(oauthUser.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("This account is created using google!");


        }
        else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
           String email = oauthUser.getAttribute("email") != null ? 
           oauthUser.getAttribute("email").toString() : oauthUser.getAttribute("login").toString() + "@github.com";
           String picture = oauthUser.getAttribute("avatar_url").toString();
           String name = oauthUser.getAttribute("login").toString();
           String providerUserId = oauthUser.getName();

           user.setEmail(email); 
           user.setProfilePicture(picture);
           user.setName(name);
           user.setProviderUserId(providerUserId);
           user.setProvider(Providers.GITHUB);
           user.setAbout("this account is set using github");
        }
        else if (authorizedClientRegistrationId.equalsIgnoreCase("linkedin")) {

        }
        else {
            logger.info("OAuthAuthenticationProvider: unknown provider");
        }

        
        /*
        // after login with oAuth save data to db->
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        // here we printed the incomming user details from google sign in 
        logger.info(user.getName());

        user.getAttributes().forEach((key, value) -> {
            logger.info("{}=>{}", key, value);
        });
        logger.info(user.getAuthorities().toString());

        // data to db save->
        String email = user.getAttribute("email").toString();
        String name = user.getAttribute("name").toString();
        String picture = user.getAttribute("picture").toString();

        //create user and save to database using these extracted info
        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setProfilePicture(picture);
        user1.setPassword("password");
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setProviderUserId(user.getName());
        user1.setRolesList(List.of(AppConstants.ROLE_USER));
        user1.setAbout("this account is created using google...");

        User user2 = userRepository.findByEmail(email).orElse(null);
        if (user2 == null) {
            userRepository.save(user1);
            logger.info("user saved !");
        }
        */
        User user2 = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (user2 == null) {
            userRepository.save(user);
            logger.info("user saved !");
        }

          new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");


    }    


}
