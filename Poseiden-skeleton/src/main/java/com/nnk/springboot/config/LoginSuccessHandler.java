package com.nnk.springboot.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nnk.springboot.domain.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Manage what happen when login is succesfull
 * */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException {

    SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();

    String redirectURL = request.getContextPath();

    if (userDetails.hasRole("ROLE_ADMIN")) {
      redirectURL = "user/list";
    } else {
      redirectURL = "bidList/list";
    }

    response.sendRedirect(redirectURL);

  }

}
