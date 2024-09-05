package com.Hunar_factory.jwt;

import com.Hunar_factory.model.factory.User;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import static com.Hunar_factory.constants.SecurityConstants.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC256;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private   String SECRET;
    @Value("${jwt.expiration}")
    public long EXPIRATION_TIME;

    public String generateToken(User user) {
        String[] claims = getClaimsFromUser(user);
        return JWT.create().withIssuer(AKAR_ARKAN).withAudience(AKAR_ARKAN_ADMINISTRATION)
                .withIssuedAt(new Date()).withSubject(user.getUsername())
                .withClaim(ID_CLAIM, user.getId())
                .withClaim(ROLE, user.getRole())
                .withArrayClaim(AUTHORITIES , claims).withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET.getBytes()));

    }


    private String[] getClaimsFromUser(User user) {
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : user.getAuthorities()){
            authorities.add(grantedAuthority.getAuthority());
        }

        return authorities.toArray(new String[0]);
    }
    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken userPasswordAuthToken = new
                UsernamePasswordAuthenticationToken(username, null, authorities);
        userPasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return userPasswordAuthToken;
    }
    public boolean isTokenValid(String username, String token) {
        JWTVerifier verifier = getJWTVerifier();
        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
    }
    public String getSubject(String token) {
        try {
            JWTVerifier verifier = getJWTVerifier();
            return verifier.verify(token).getSubject();
        } catch (JWTDecodeException e) {
            System.err.println("Token parsing error: " + e.getMessage());
            throw new IllegalArgumentException("Invalid token");
        }
    }


    private JWTVerifier getJWTVerifier() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET); // Ensure SECRET is correct and same as the one used for signing
            return JWT.require(algorithm)
                    .withIssuer(AKAR_ARKAN) // Ensure this matches the issuer used when creating the JWT
                    .build();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("JWT verification failed: " + exception.getMessage());
        }
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }
    public List<GrantedAuthority> getAuthorities(String token) {
        String[] claims = getAuthFromToken(token);
        return Arrays.stream(claims)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public String[] getAuthFromToken(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getClaim("authorities").asArray(String.class);
    }

    public Long getUserIdFromToken(String token) {
        DecodedJWT decodedJWT = decodeToken(token);
        return decodedJWT.getClaim(ID_CLAIM).asLong();
    }


    private DecodedJWT decodeToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .withIssuer(AKAR_ARKAN)
                .build();
        return verifier.verify(token);
    }

}
