package raf.teamEpic.service;

import io.jsonwebtoken.Claims;

public interface TokenService {
    public String generate(Claims claims);
    public Claims parseToken(String jwt);
}
