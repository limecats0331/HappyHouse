package com.ssafy.happyhouse.security;

import com.ssafy.happyhouse.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Component
public class JwtProvider {
//    @Value("${jwt.secret-key}")
    private String secretKey;


    private String createToken(Map<String, Object> claims) {
        String secretKeyEncodeBase64 = Encoders.BASE64.encode(secretKey.getBytes());
        byte[] keyBytes = Decoders.BASE64.decode(secretKeyEncodeBase64);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .signWith(key)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .compact();
    }

    private Claims extractAllClaims(String token) {
        if (StringUtils.isEmpty(token)) return null;
        String secretKeyEncodeBase64 = Encoders.BASE64.encode(secretKey.getBytes());
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder().setSigningKey(secretKeyEncodeBase64).build().parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            claims = null;
        }
        return claims;
    }

    public String extractUsername(String token) {
        final Claims claims = extractAllClaims(token);
        if (claims == null) return null;
        else return claims.get("username",String.class);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userEmail", user.getEmail());
        return createToken(claims);
    }
}
