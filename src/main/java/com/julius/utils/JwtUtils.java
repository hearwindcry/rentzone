package com.julius.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

//    static final String sk = "panda";

    // TODO: 私钥需要统一
    public static String getToken(Map<String,String> map) {

        return Jwts.builder()
                .subject("user")
                .claims(map)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureUtils.keyPair().getPrivate(), Jwts.SIG.RS256) // 使用私钥进行签名，选择签名算法RS256
                .compact();
    }

    public static Object getObject(String token) {
        Claims claims = Jwts.parser()
                .decryptWith(SignatureUtils.keyPair().getPrivate())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        UserDetails securityDetail = claims.get("a", UserDetails.class);
        return securityDetail;
    }
}
