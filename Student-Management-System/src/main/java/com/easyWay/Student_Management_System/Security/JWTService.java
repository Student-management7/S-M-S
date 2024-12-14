package com.easyWay.Student_Management_System.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTService {

    private final SecretKey secretKey ;
    private static final Set<String> BLACKLISTED_TOKENS = new HashSet<>();

    public JWTService() {
        //    @Value("${jwt.secret}")
        String secretKeyBase64 = "U2FsdGVkX19gZWF1Z9cA4O6qR9cB2b8sbT0t3U8IwOQ";
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyBase64));
    }

    public boolean isTokenBlacklisted(String token) {
        return BLACKLISTED_TOKENS.contains(token);
    }

    public void blacklistToken(String token) {
        BLACKLISTED_TOKENS.add(token);
    }

    public SecretKey getKey() {
        return this.secretKey;
    }

    public String  generateToken(String  userName  , String permission, String schoolCode){
        Map<String  ,Object> claims = new HashMap<>();
        claims.put("permission" , permission);
        claims.put("schoolCode", schoolCode);
       return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ( 60 * 60 * 1000)))
                .and()
                .signWith(getKey())
                .compact();
    }

    public String extractUserName(String token) {

        return extractClaim(token , Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims , T> claimsTFunction) {
        final Claims claims = extractAllClaim(token);
        return  claimsTFunction.apply(claims);
    }

    private Claims extractAllClaim(String token)  throws SignatureException{

            return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();

    }
    public String getPermissionsFromToken(String token) throws SignatureException{

            Claims claims = Jwts.parser()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String premission = (String) claims.get("permission");
            if (premission ==null){
                throw new SignatureException("sign");
            }
            return  premission;
    }

    public boolean validateToken(String token ) {
        final  String userName = extractUserName(token);
        return  !isTokenExpired(token) && !isTokenBlacklisted(token);
    }

    private boolean isTokenExpired(String token) {

        return extractExpiraton(token).before(new Date());
    }

    private Date extractExpiraton(String token) {
        return extractClaim(token , Claims::getExpiration);
    }
}
