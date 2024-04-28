package raf.teamEpic.security;

import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import raf.teamEpic.domain.Client;
import raf.teamEpic.domain.Manager;
import raf.teamEpic.domain.User;
import raf.teamEpic.dto.ClientCreateDto;
import raf.teamEpic.dto.ManagerCreateDto;
import raf.teamEpic.repository.UserRepository;
import raf.teamEpic.service.TokenService;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Configuration
public class SecurityAspect {

    @Value("${oauth.jwt.secret}")
    private String jwtSecret = "secret_key";

    private TokenService tokenService;
    private UserRepository userRepository;

    public SecurityAspect(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Around("@annotation(raf.teamEpic.security.CheckSecurity)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String token = null;

        for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
            if (methodSignature.getParameterNames()[i].equalsIgnoreCase("authorization")) {
                if (joinPoint.getArgs()[i].toString().startsWith("Bearer")) {
                    token = joinPoint.getArgs()[i].toString().split(" ")[1];
                }
            }
        }

        if(token == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Claims claims = tokenService.parseToken(token);
        if (claims == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        CheckSecurity checkSecurity = method.getAnnotation(CheckSecurity.class);
        String type = claims.get("type", String.class);
        if (Arrays.asList(checkSecurity.roles()).contains(type)) {
            return joinPoint.proceed();
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Around("@annotation(raf.teamEpic.security.CheckClient)")
    public Object checkClient(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String token = null;
        ClientCreateDto dto = null;

        for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
            if (methodSignature.getParameterNames()[i].equalsIgnoreCase("authorization")) {
                if (joinPoint.getArgs()[i].toString().startsWith("Bearer")) {
                    token = joinPoint.getArgs()[i].toString().split(" ")[1];
                }
            }
            if(methodSignature.getParameterNames()[i].equals("dto")){
                dto = (ClientCreateDto) joinPoint.getArgs()[i];
            }
        }

        if(token == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Claims claims = tokenService.parseToken(token);
        if (claims == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(dto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> check = userRepository.findUserByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        Client client;
        if(check.isPresent()){
            client = (Client) check.get();
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Long dtoId = client.getId();
        Long tokenId = Long.valueOf(claims.getId());
        if(tokenId.equals(dtoId)){
            return joinPoint.proceed();
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Around("@annotation(raf.teamEpic.security.CheckManager)")
    public Object checkManager(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String token = null;
        ManagerCreateDto dto = null;
        for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
            if (methodSignature.getParameterNames()[i].equalsIgnoreCase("authorization")) {
                if (joinPoint.getArgs()[i].toString().startsWith("Bearer")) {
                    token = joinPoint.getArgs()[i].toString().split(" ")[1];
                }
            }
            if(methodSignature.getParameterNames()[i].equals("dto")){
                dto = (ManagerCreateDto) joinPoint.getArgs()[i];
            }
        }

        if(token == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Claims claims = tokenService.parseToken(token);
        if (claims == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(dto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> check = userRepository.findUserByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        Manager manager;
        if(check.isPresent()){
            manager = (Manager) check.get();
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Long dtoId = manager.getId();
        Long tokenId = Long.valueOf(claims.getId());
        if(tokenId.equals(dtoId)){
            return joinPoint.proceed();
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
