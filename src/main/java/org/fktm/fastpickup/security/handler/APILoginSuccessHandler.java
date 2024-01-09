package org.fktm.fastpickup.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.fktm.fastpickup.member.dto.MemberDTO;
import org.fktm.fastpickup.util.jwt.JWTUtil;
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

            Map<String, Object> claims = memberDTO.getClamis();

            String accessToken = JWTUtil.generateToken(claims, 1);
            String refreshToken = JWTUtil.generateToken(claims, 60*24);

            claims.put("accessToken", accessToken);
            claims.put("refreshToken", refreshToken);

            Gson gson = new Gson();

            String jsonStr = gson.toJson(claims);

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonStr);
            printWriter.close();

    }
    
}
