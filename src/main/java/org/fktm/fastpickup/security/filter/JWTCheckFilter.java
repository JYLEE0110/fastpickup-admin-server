package org.fktm.fastpickup.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.fktm.fastpickup.member.dto.MemberDTO;
import org.fktm.fastpickup.util.jwt.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{

        String path = request.getRequestURI();
        
        //PreFlight도 접근 가능
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }

        // 누구나 접근 가능
        if(path.equals("/api/member/login")){
            return true;
        }

        if(path.equals("/api/product/list")){
            return true;
        }

        if(path.equals("/api/order/create")){
            return true;
        }

        return false;

    }
    
    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain) throws ServletException, IOException {
    
            log.info("===== doFilterInternal=====");

            // 매 요청 마다 Authrization 헤더 값을 가져온다.
            String authHeaderStr = request.getHeader("Authorization");
    
            try{
                //Authrization 헤더에서 Bearer를 잘라 accessToken값 만가져온다.
                String accessToken = authHeaderStr.substring(7);

                // accessToken값을 인자로 validateToken 메소드를 불러 사용자 정보를 가져온다.
                Map<String, Object> claims = JWTUtil.validateToken(accessToken);

                log.info("JWT claims : " + claims);

                String memberID = (String) claims.get("memberID");
                String memberPW = (String) claims.get("memberPW");
                List<String> roleNames = (List<String>) claims.get("roleNames");
            
                // MemberDTO의 상위 객체는 User => 시큐리티 적용할 username, password 등
                MemberDTO memberDTO = new MemberDTO(memberID, memberPW, roleNames);

                // 
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(memberDTO, memberPW, memberDTO.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                filterChain.doFilter(request, response);

            }catch(Exception e){
                log.error("JWT Check Error..............");
                log.error(e.getMessage());

                Gson gson = new Gson();
                String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

                response.setContentType("application/json");
                PrintWriter printWriter = response.getWriter();
                printWriter.println(msg);
                printWriter.close();
            }

        }

    
}
