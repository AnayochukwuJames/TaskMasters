package com.example.taskmasters.service.serviceImp;

import com.example.taskmasters.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
@RequiredArgsConstructor
public class JWTServiceImp implements JWTService {
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .signWith(SignatureAlgorithm.HS256, String.valueOf(SECRET_KEY))
                .compact();
    }
    public String generateRefreshToken(Map<String, Object> extraClaim, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaim).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, String.valueOf(SECRET_KEY))
                .compact();
    }
    public String extractUserName (String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolvers){
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }


    private Key getSignKey(){
        byte [] key = Decoders.BASE64.decode("294A404E635266556A586E3272357538782F413F4428472B4B6150645367566B");
        return Keys.hmacShaKeyFor(key);

    }
    private Claims extractAllClaims(String token){
          return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims ::getExpiration).before(new Date());
    }


}
