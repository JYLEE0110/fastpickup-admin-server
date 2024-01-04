package org.fktm.fastpickup.security.handler;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class APILoginFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", Calendar.getInstance().getTime());

        if (exception instanceof InternalAuthenticationServiceException) {
            // 로그인 실패 및 인증 실패에 대한 예외 처리

            data.put("exception", "NotFound_USER");
            data.put("message", "Diffrent ID or PassWord");

        } else if (exception instanceof BadCredentialsException) {

            data.put("exception", "Not_Found_USER");
            data.put("message", "Diffrent ID or PassWord");

        }

        response.getOutputStream().println(objectMapper.writeValueAsString(data));
    }

}
