package org.pancakeapple.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.pancakeapple.constant.RBACConstant;
import org.pancakeapple.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT令牌认证过滤器
 * Author SKY 2023/9/19
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(RBACConstant.AUTH_HEADER);
        String jwt=null;

        if(StringUtils.hasText(token) && token.startsWith(RBACConstant.AUTH_TOKEN)) {
            jwt=token.substring(RBACConstant.AUTH_TOKEN.length()+1);
        }
        if (jwt!=null && JwtUtils.validateJwt(jwt)) {
            Claims claims = JwtUtils.parseJWT(jwt);
            String username = claims.get(RBACConstant.USER_NAME, String.class);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //只有在JWT令牌存在且有效时,才授予相应的的权限
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }
        filterChain.doFilter(request,response);
    }
}
