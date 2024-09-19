package jwlee.crudpractice.crud.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {


    private static final String secretKey = "6b479e014a1fcd7fe2f1b5ef508f9479970bf595c6389ce5a82b5de4cfcbd5ce";

    private Key getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(Claims claims, LocalDateTime expireAt, Long userNo) {
        var key = getKey();

        var expireTime = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());

        String token = Jwts.builder()
                .claim("userNo", userNo)
                .setExpiration(expireTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    // JWT 토큰에서 클레임 추출
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Claims claims = extractClaims(token);
            return claims.getExpiration().after(new Date()); // 만료 시간 확인
        } catch (Exception e) {
            return false; // 유효하지 않은 경우 false 반환
        }
    }

}
