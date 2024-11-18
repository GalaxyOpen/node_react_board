package com.lkh.board_back.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtProvider {

    @Value("%{secret-key}")
    private String secretKey;

    // JWT 생성 메소드
    public String create(String email){
        // amountToAdd(만료기간) : 1시간으로 잡아놓음 
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        String jwt = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
            .compact();
        
        return jwt;
    }

    // JWT 검증 메소드 
    public String validate(String jwt) {
        Claims claims = null;
        
        try {
            claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(jwt).getBody();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return claims.getSubject();
    }
}
