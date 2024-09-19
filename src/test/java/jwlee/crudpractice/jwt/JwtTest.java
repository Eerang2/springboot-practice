package jwlee.crudpractice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jwlee.crudpractice.common.BaseTest;
import jwlee.crudpractice.crud.jwt.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureMockMvc(addFilters = false)
public class JwtTest extends BaseTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    @DisplayName("예시번호로 토근 생성")
    void testCreateToken() {
        // Given
        Claims claims = Jwts.claims();
        LocalDateTime expireAt = LocalDateTime.now().plusHours(2); // 2시간 후 만료
        Long userNo = 1L; // 예시로 사용자 고유 번호 1

        // When
        String token = jwtUtil.createToken(claims, expireAt, userNo);

        // Then
        assertNotNull(token); // 토큰이 null이 아닌지 확인
        System.out.println("Generated Token: " + token);
    }

    @Test
    @DisplayName("테스트")
    void testGetToken() {
        System.out.println("aaaaaaaa");
    }
}
