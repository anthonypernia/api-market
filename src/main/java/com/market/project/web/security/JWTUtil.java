package com.market.project.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static final String KEY = "HASH";

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,KEY).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUserName(token))&&!isTokenExpired(token);
    }

    //Extrae el nombre de usuario
    public String extractUserName(String token){
        return getClaims(token).getSubject();
    }
    //Valida que el token no este vencido
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    //Desencripta la data que se recibe en el token
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

}
