package tipitapi.drawmytodayimprovement.common.config;

import io.swagger.v3.core.util.PrimitiveType;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "오늘 하루를 그려줘 API 문서",
                description = "프로그라피 8기 4팀 TipiTapi의 오늘 하루를 그려줘 프로젝트의 API 문서입니다.",
                version = "v1"),
        servers = {
                @Server(url = "https://draw-my-today-dev.devstory.co.kr", description = "테스트 서버"),
                @Server(url = "https://draw-my-today.devstory.co.kr", description = "운영 서버")
        }
)
@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

    static {
        // Swagger UI LocalTime 타입 문제 https://github.com/swagger-api/swagger-core/issues/2992
        PrimitiveType.customClasses().put("java.time.LocalTime", PrimitiveType.PARTIAL_TIME);
    }

    @Bean
    public GroupedOpenApi usersOpenApi() {
        String[] paths = {"/user/**"};

        return GroupedOpenApi
                .builder()
                .group("유저 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi diaryOpenAPi() {
        String[] paths = {"/diary/**"};

        return GroupedOpenApi
                .builder()
                .group("일기 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi emotionsOpenAPi() {
        String[] paths = {"/emotion/**"};

        return GroupedOpenApi
                .builder()
                .group("감정 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi oauth2OpenAPi() {
        String[] paths = {"/oauth2/**"};

        return GroupedOpenApi
                .builder()
                .group("OAuth2 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi devOpenAPi() {
        String[] paths = {"/dev/**"};

        return GroupedOpenApi
                .builder()
                .group("개발 환경 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi healthOpenAPi() {
        String[] paths = {"/health/**"};

        return GroupedOpenApi
                .builder()
                .group("Health Check API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi adOpenAPi() {
        String[] paths = {"/ad/**"};

        return GroupedOpenApi
                .builder()
                .group("광고 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi adminOpenAPi() {
        String[] paths = {"/admin/**"};

        return GroupedOpenApi
                .builder()
                .group("관리자용 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi imageOpenApi() {
        String[] paths = {"/image/**"};

        return GroupedOpenApi
                .builder()
                .group("일기 이미지 API")
                .pathsToMatch(paths)
                .build();
    }
}