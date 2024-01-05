package org.fktm.fastpickup.util.jwt;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {
    
    private static String key = "fktm2172511733@12341234123412341234123421";

    public static String generateToken(Map<String, Object> valueMap, int min){

        SecretKey key = null;

        // key를 해쉬 알고리즘으로 암호화 시킴
        try{
            key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

        //JWT 생성하는 로직 토큰 발급 시간과 만료시간을 세팅, 암호화한 key값도 세팅한다.
        // Claims는 회원정보
        String jwtStr = Jwts.builder()
                .setHeader(Map.of("typ", "JWT"))
                .setClaims(valueMap)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key)
                .compact();
        
                return jwtStr;
    }

}
