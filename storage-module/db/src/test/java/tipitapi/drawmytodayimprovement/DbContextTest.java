package tipitapi.drawmytodayimprovement;

import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import tipitapi.drawmytodayimprovement.enumeration.SocialCode;
import tipitapi.drawmytodayimprovement.table.diary.repository.DiaryJpaRepository;
import tipitapi.drawmytodayimprovement.table.emotion.repository.EmotionJpaRepository;
import tipitapi.drawmytodayimprovement.table.image.repository.ImageJpaRepository;
import tipitapi.drawmytodayimprovement.table.prompt.repository.PromptJpaRepository;
import tipitapi.drawmytodayimprovement.table.user.entity.UserEntity;
import tipitapi.drawmytodayimprovement.table.user.repository.UserJpaRepository;

@ActiveProfiles("local")
@DataJpaTest
@Tag("context")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public abstract class DbContextTest {

    @Autowired
    protected UserJpaRepository userJpaRepository;
    @Autowired
    protected DiaryJpaRepository diaryJpaRepository;
    @Autowired
    protected EmotionJpaRepository emotionJpaRepository;
    @Autowired
    protected ImageJpaRepository imageJpaRepository;
    @Autowired
    protected PromptJpaRepository promptJpaRepository;

    protected UserEntity createUserEntity(String email) {
        UserEntity userEntity = UserEntity.builder()
                .socialCode(SocialCode.GOOGLE)
                .email(email)
                .build();
        return userJpaRepository.save(userEntity);
    }
}
