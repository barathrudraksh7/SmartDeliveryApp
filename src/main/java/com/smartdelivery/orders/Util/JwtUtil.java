package com.smartdelivery.orders.Util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final long EXPIRATION_TIME = 86400000;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    //this is called when the API authenticates for the 1st time
    public String generateToken(String username){
        return Jwts.builder() //used to build
            .setSubject(username)
            .setIssuer("SmartDeliveryAPI")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(key)
            .compact(); //compact this into a String
    }

    //this is called for the subsequent request after authentication
    public String validateTokenandGetUsername(String token){
        return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject(); //get the body of the token and extract the username i.e. Subject
    }
}
