package jwlee.crudpractice.crud.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jwlee.crudpractice.crud.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private Long id;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email
    private String email;

    @Builder
    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .name(this.name)
                .email(this.email)
                .build();
    }
}
