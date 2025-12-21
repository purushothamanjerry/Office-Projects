package com.officeproject.backend.Utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // 🔐 Secret key (generated once per app run)
    private static final Key key =
            Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ⏰ 30 minutes
    private static final long EXPIRATION_TIME = 30 * 60 * 1000;

    /* ================== TOKEN GENERATION ================== */

    public static String generateToken(String userId, String email) {

        return Jwts.builder()
                .setSubject(userId)
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    /* ================== TOKEN VALIDATION ================== */

    public static boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /* ================== EXTRACT CLAIMS ================== */

    private static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String extractUserId(String token) {
        return getClaims(token).getSubject();
    }

    public static String extractEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    /* ================== EXPIRY CHECK ================== */

    public static boolean isTokenExpired(String token) {
        try {
            Date expiry = getClaims(token).getExpiration();
            return expiry.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    /* ================== REFRESH TOKEN ================== */

    public static String refreshToken(String token) {

        Claims claims = getClaimsAllowExpired(token);

        String userId = claims.getSubject();
        String email = claims.get("email", String.class);

        return generateToken(userId, email);
    }

    private static Claims getClaimsAllowExpired(String token) {
        try {
            return getClaims(token);
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
