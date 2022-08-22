package io.github.pedroermarinho.comandalivreapi.infra.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;


    public String generateToken(String email) {
        final long expiration = 259200000L;
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token) {
        Claims claims = getClaimsToken(token);
        if (claims != null) {
            String email = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            return email != null && expiration != null && now.before(expiration);
        }
        return false;
    }

    private Claims getClaimsToken(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsToken(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }
}
