package org.fktm.fastpickup.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import org.fktm.fastpickup.member.dto.MemberDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

            MemberDTO memberDTO = (MemberDTO)authentication.getPrincipal();

            log.info(memberDTO);

            // Gson gson = new Gson();

            // String jsonStr = gson.toJson(memberDTO);

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(memberDTO);
            printWriter.close();

    }
    
}
