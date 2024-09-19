package jwlee.crudpractice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jwlee.crudpractice.common.BaseMockMvcTest;
import jwlee.crudpractice.crud.controller.MemberController;
import jwlee.crudpractice.crud.jwt.JwtUtil;
import jwlee.crudpractice.crud.model.dto.UserDto;
import jwlee.crudpractice.crud.model.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberControllerTest extends BaseMockMvcTest {

    @Mock
    private MemberService memberService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    void set() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("회원가입 성공")
    void signUpSuccess() throws Exception {
        // Given
        UserDto userDto = UserDto.builder()
                .userId("testUser")
                .userName("이은섭")
                .password("qwer1234!")
                .confirmPwd("qwer1234!")
                .build();

        doNothing().when(memberService).saveUser(any(UserDto.class));
        when(memberService.findUserNoById(anyString())).thenReturn(1L);
        when(jwtUtil.createToken(any(Claims.class), any(LocalDateTime.class), anyLong()))
                .thenReturn("mocked-jwt-token");

        // When
        // Then
        MvcResult result = mockMvc.perform(post("/signup")
                        .flashAttr("userDto", userDto))
                .andExpect(status().is3xxRedirection())  // 리다이렉션 확인
                .andExpect(redirectedUrl("/signup-success"))  // 성공 페이지 리다이렉션
                .andExpect(cookie().exists("jwtToken"))  // 쿠키 존재 확인
                .andExpect(cookie().value("jwtToken", "mocked-jwt-token"))  // JWT 토큰 값 확인
                .andReturn();

        // 쿠키 정보 확인
        Cookie jwtCookie = result.getResponse().getCookie("jwtToken");
        assert jwtCookie != null;
        assert jwtCookie.isHttpOnly();      // HttpOnly 설정 확인
        assert jwtCookie.getMaxAge() == 7200;   // 만료 시간 2시간

    }

    @Test
    public void signUpSuccess1() throws Exception {
        // 요청 실행
        MvcResult result = mockMvc.perform(post("/signup")
                        .param("userId", "testUser1")
                        .param("password", "qwer1234!")
                        .param("passwordConfirm", "qwer1234!"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        // 쿠키에서 JWT 토큰을 가져옴
        Cookie jwtCookie = result.getResponse().getCookie("jwtToken");
        assertNotNull(jwtCookie);

        // JWT 토큰을 해석하여 클레임을 확인
        String token = jwtCookie.getValue();
        Claims claims = Jwts.parser()
                .setSigningKey("your-signing-key")  // JWT를 생성할 때 사용한 시크릿 키
                .parseClaimsJws(token)
                .getBody();

        // 클레임에서 userNo를 가져와서 검증
        Long userNo = claims.get("userNo", Long.class);
        assertEquals(1L, userNo);  // 예상한 userNo와 비교
    }



}
