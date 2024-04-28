package raf.teamEpic.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImplementation implements TokenService{

    @Value("${oauth.jwt.secret}")
    private static String jwtSecret;

    @Override
    public String generate(Claims claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }

    @Override
    public Claims parseToken(String jtw) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jtw).getBody();
        } catch (Exception e){
            return null;
        }
        return claims;
    }
}
