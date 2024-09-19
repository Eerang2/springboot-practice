package jwlee.crudpractice.crud.service;


import jwlee.crudpractice.crud.domain.user.UserRepository;
import jwlee.crudpractice.crud.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long userSave(UserDto userCreate) {
        return userRepository.save(userCreate.toEntity()).getId();

    }
}
