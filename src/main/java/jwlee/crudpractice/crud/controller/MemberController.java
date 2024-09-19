package jwlee.crudpractice.crud.controller;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jwlee.crudpractice.crud.jwt.JwtUtil;
import jwlee.crudpractice.crud.model.dto.UserDto;
import jwlee.crudpractice.crud.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    @PostMapping("/save")
    public String signUp(@Valid @ModelAttribute("userDto") UserDto userDto,
                         BindingResult bindingResult,
                         HttpServletResponse response) {

        log.info("userDto: {}", userDto);
        // lombok 에러 처리
        if(bindingResult.hasErrors()) {
            System.out.println("aaa");
            return "signup";
        }
        // 비밀번호 확인 에러
        if (!userDto.passwordMatch()) {
            bindingResult.rejectValue("passwordConfirm", "error.userDto", "비밀번호가 일치하지않습니다.");
            System.out.println("bbb");
            return "signup";
        }

        memberService.saveUser(userDto);

        Claims claims = Jwts.claims();  // 클레임 생성
        LocalDateTime expireAt = LocalDateTime.now().plusHours(2);  // 토큰 만료시간 2시간 후
        Long userNo = memberService.findUserNoById(userDto.getUserId());  // 사용자 고유 번호를 가져옴

        String token = jwtUtil.createToken(claims, expireAt, userNo);  // 토큰 생성

        // 토큰을 쿠키에 저장
        Cookie jwtCookie = new Cookie("jwtToken", token);
        jwtCookie.setHttpOnly(true); // 클라이언트 측에서 자바스크립트로 접근할 수 없게 설정
        jwtCookie.setMaxAge(60 * 60 * 2); // 2시간
        jwtCookie.setPath("/"); // 모든 경로에서 사용 가능
        response.addCookie(jwtCookie);

        return "redirect:/signup-success";
    }

    @GetMapping("signup-success")
    public String loginForm() {
        return "login";
    }
}
