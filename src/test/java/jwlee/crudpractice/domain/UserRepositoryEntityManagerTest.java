package jwlee.crudpractice.domain;


import jakarta.transaction.Transactional;
import jwlee.crudpractice.common.BaseTest;
import jwlee.crudpractice.crud.domain.user.User;
import jwlee.crudpractice.crud.domain.user.UserRepository;
import jwlee.crudpractice.crud.domain.user.UserRepositoryEntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@Rollback(value = false)
public class UserRepositoryEntityManagerTest extends BaseTest {

    @Autowired
    private UserRepositoryEntityManager userRepositoryEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 엔티티메니저로_find() {
        User user = userRepositoryEntityManager.findById(1L);
        assertEquals("이진우", user.getUserName());
    }

    @Test
    @DisplayName("엔티티 매니저로 insert save")
    public void saveTest() {
        User user1 = User.builder()
                .userName("이어진")
                .build();
        userRepositoryEntityManager.save(user1);
    }

    @Test
    @DisplayName("엔티티 매니저로 update save")
    public void updateTest() {
        // given
        User user = userRepositoryEntityManager.findById(7L);
        user.setUserName("이진우");

        // when
        userRepositoryEntityManager.save(user);

        // then
        assertEquals("이진우", user.getUserName());
    }

//    @Test
//    @DisplayName("엔티티 매니저로 영속성 컨텍스트 update save")
//    public void persistContextUpdateTest() {
//        // given
//        User user = userRepositoryEntityManager.findById(7L);
//
//        // when
//        user.setEmail("jinwoo@naver.com");
//
//        // then
//        assertEquals("jinwoo@naver.com", user.getEmail());
//    }
}
