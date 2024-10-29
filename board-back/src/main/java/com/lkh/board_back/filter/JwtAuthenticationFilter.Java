package com.lkh.board_back.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import.javax.servlet.ServletException;
import.javax.servlet.http.HttpServletRequest;
import.javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lkh.board_back.provider.JwtProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Overide
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        try{
            String token = parseBearerToken(request);
        
            if (token == null){
                FilterChain.doFilter(request, response);
                return;
            }
    
            String email = jwtProvider.validate(token);
    
            if(email == null){
                FilterChain.doFilter(request, response);
                return;
            }
    
            AbstractAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
            authenticationToken.setDetails(new WebAuthenticationDetailsSources().buildDetails(request));
            
            SecurityContext SecurityContext = SecurityContextHolder.createEmptyContext();
            SecurityContext.setAuthentication(authenticationToken);
    
            SecurityContextHolder.SetContext(SecurityContext);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        
        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        boolean hasAuthorization = StringUtils.hasText(authorization);

        if(!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer");
        if(!isBearer) return null;

        String token = authorization.substring(7);
        return token; 
        
    }

}