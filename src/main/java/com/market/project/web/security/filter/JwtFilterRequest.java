package com.market.project.web.security.filter;

import com.market.project.domain.service.PlatziUtilDetailService;

import com.market.project.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    private PlatziUtilDetailService platziUtilDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            String jwt = authorizationHeader.substring(7, authorizationHeader.length());
            String userName = jwtUtil.extractUserName(jwt);

            //Validamos que el usuario no sea nulo y que aun no este autenticado en la aplicación
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //Valida que el usuario exista en el sistema de autenticación
                UserDetails userDetails = platziUtilDetailService.loadUserByUsername(userName);

                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }

            }
        }

        filterChain.doFilter(request, response);
    }
}
