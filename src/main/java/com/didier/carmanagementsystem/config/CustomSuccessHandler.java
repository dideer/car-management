package com.didier.carmanagementsystem.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        var authorities = authentication.getAuthorities();
        var roles =authorities.stream().map(r ->r.getAuthority()).findFirst();

        if (roles.orElse("").equals("ROLE_CEO")) {
            response.sendRedirect("/ceo/employees/");
        } else if (roles.orElse("").equals("ROLE_MANAGER")) {
            response.sendRedirect("/manage/");
        }else if (roles.orElse("").equals("ROLE_SALESPERSON")) {
            response.sendRedirect("/salesperson/");
        }else {
            response.sendRedirect("/error");
        }



    }
}
