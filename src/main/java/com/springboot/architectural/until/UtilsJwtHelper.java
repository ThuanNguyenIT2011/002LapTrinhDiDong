package com.springboot.architectural.until;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class UtilsJwtHelper {
    @Value("${jwk.privateKey}")
    private String privateKey;

    public String generateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
        return jws;
    }

    public boolean verifyToken(String token){
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parse(token);
            return true;
        } catch (Exception ex){
            return false;
        }
    }
}
