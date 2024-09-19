package jwlee.crudpractice.crud.model.service;


import jwlee.crudpractice.crud.domain.user.User;
import jwlee.crudpractice.crud.domain.user.UserRepository;
import jwlee.crudpractice.crud.model.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void saveUser(UserDto userDto) {

        String pwd = userDto.getPassword();
        String confirmPwd = userDto.getConfirmPwd();
        if(pwd.equals(confirmPwd)) {
            String encodePwd = passwordEncoder.encode(pwd);
            User user = User.builder()
                    .userId(userDto.getUserId())
                    .userName(userDto.getUserName())
                    .password(encodePwd)
                    .build();
            userRepository.save(user);
        }
    }

    public Long findUserNoById(String userId) {
        // 회원의 고유 번호를 반환
         return userRepository.findUserNoById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디의 회원이 존재하지 않습니다."));
    }
}
