package raf.teamEpic.service.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import raf.teamEpic.service.TokenService;

@Service
public class TokenServiceImplementation implements TokenService {

    @Value("${oauth.jwt.secret}")
    private static String jwtSecret = "secret_key";
    @Override
    public String generate(Claims claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }

    @Override
    public Claims parseToken(String jwt) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody();
        } catch (Exception e){
            return null;
        }
        return claims;
    }
}
