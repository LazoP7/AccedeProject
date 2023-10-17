package project.accede.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import project.accede.security.dto.JwtDTO;

import java.security.Key;

import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    @Value("${jwt.refreshMs}")
    private int jwtRefreshMs;


    public JwtDTO generateJwtToken(Authentication authentication, boolean rememberMe) {

        String authorities = authentication.getAuthorities().stream().
                map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS512)
                .compact();


        String refreshToken = null;
        if (rememberMe) {
            refreshToken = Jwts.builder()
                    .setSubject(authentication.getName())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtRefreshMs))
                    .signWith(key(), SignatureAlgorithm.HS512)
                    .compact();
        }

        return new JwtDTO(accessToken, refreshToken);

    }

    private Key key() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().
                parseClaimsJws(token).getBody().getSubject();

    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public JwtDTO generateJwtTokenWith2Fact(String username) {
        String accessToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS512)
                .compact();

        return new JwtDTO(accessToken, null);
    }

}
