package jwlee.crudpractice.crud.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jwlee.crudpractice.crud.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {


        private Long userNo;

        @NotBlank(message = "아이디를 입력해주세요")
        @Pattern(regexp = "^[a-zA-Z0-9]{7,15}$", message = "아이디는 영문과 숫자를 포함한 7~15글자여야 합니다.")
        private String userId;

        @NotBlank(message = "이름을 입력해주세요.")
        @Pattern(regexp = "^[가-힣]{3,10}$", message = "이름은 한글 3~10글자여야 합니다.")
        private String userName;

        @NotBlank(message = "비밀번호를 입력하세요.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z\\d!@#$%^&*]{8,20}$",
                message = "비밀번호는 영문, 숫자, 특수문자를 포함한 8~20글자여야 합니다.")
        private String password;

        private String confirmPwd;

        public boolean passwordMatch() {
            return password.equals(confirmPwd);

        }

        @Builder
        public UserDto(String userName, String userId, String password, String confirmPwd) {
            this.userName = userName;
            this.userId = userId;
            this.password = password;
            this.confirmPwd = confirmPwd;
        }

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .password(password)
                    .userName(this.userName)
                    .build();
    }
}
