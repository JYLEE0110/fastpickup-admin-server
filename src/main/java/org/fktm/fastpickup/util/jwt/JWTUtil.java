package org.fktm.fastpickup.util.jwt;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
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

    // JWT Access토큰을 파싱하여 payload 정보들을 얻기위한 코드
    public static Map<String, Object> validateToken(String token){

        Map<String, Object> claim = null;
        try{
            // SecretKey를 암호화해서 얻음
            SecretKey key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));

            // 암호화된 secretKey로 토큰을 파싱 하여 payload를 얻는다.
            claim = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
        }catch(MalformedJwtException malformedJwtException){
            throw new CustomJWTException("MalFormed");
        }catch(ExpiredJwtException expiredJwtException){
            throw new CustomJWTException("Expired");
        }catch(InvalidClaimException invalidClaimException){
            throw new CustomJWTException("Invalid");
        }catch(JwtException jwtException){
            throw new CustomJWTException("JWTError");
        }catch(Exception e){
            throw new CustomJWTException("Error");
        }
        return claim;

    }

}
