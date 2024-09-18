package jwlee.crudpractice.crud.model;

import jakarta.validation.constraints.NotBlank;
import jwlee.crudpractice.crud.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class UserDto {


        @NotBlank(message = "아이디를 입력해주세요")
        private String userId;

        @NotBlank(message = "비밀번호를 입력하세요.")
        private String password;

        @NotBlank(message = "이름을 입력해주세요.")
        private String userName;


        public UserDto(String userName, String userId, String password) {
            this.userName = userName;
            this.userId = userId;
            this.password = password;
        }

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .password(password)
                    .userName(this.userName)
                    .build();
    }
}
