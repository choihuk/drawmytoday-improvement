package tipitapi.drawmytodayimprovement.user.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tipitapi.drawmytodayimprovement.DbContextTest;
import tipitapi.drawmytodayimprovement.table.user.entity.UserEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("UserJpaRepository 테스트")
class UserJpaRepositoryTest extends DbContextTest {

    @Nested
    @DisplayName("findAllByEmail 메소드 테스트")
    class FindAllByEmailTest {

        @Nested
        @DisplayName("email이 같은 유저가 없을 경우")
        class If_no_user_exists {

            @Test
            @DisplayName("빈 리스트를 반환한다.")
            void return_empty_list() {
                // given
                String email = "not_exist_email";

                // when
                List<UserEntity> userEntityList = userJpaRepository.findAllByEmail(email);

                // then
                assertThat(userEntityList).hasSize(0);
            }
        }

        @Nested
        @DisplayName("email이 같은 유저가 두 명 있을 경우")
        class If_user_exists {

            @Test
            @DisplayName("두 유저를 리스트로 반환한다.")
            void return_user_list() {
                // given
                String email = "exist_email@email.com";
                createUserEntity(email);
                createUserEntity(email);

                // when
                List<UserEntity> userEntityList = userJpaRepository.findAllByEmail(email);

                // then
                assertThat(userEntityList).hasSize(2);
            }
        }
    }

}