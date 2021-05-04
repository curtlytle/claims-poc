package com.sif.claims.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * @see <a href=https://medium.com/javarevisited/integration-tests-with-spring-boot-testcontainers-liquibase-and-junit-5-13fb1ae70b40>Medium Article</a>
 * @see <a href=https://www.baeldung.com/spring-boot-testcontainers-integration-test>Baeldung Article</a>
 */

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = TestPostgresContainer.DockerPostgreDataSourceInitializer.class)
@Testcontainers
public abstract class TestPostgresContainer {
    private static final Logger log = LoggerFactory.getLogger((TestPostgresContainer.class));
    public static PostgreSQLContainer<?> postgreDBContainer = new PostgreSQLContainer<>("postgres");

    static {
        postgreDBContainer.start();
    }

    public static class DockerPostgreDataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {

            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + postgreDBContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreDBContainer.getUsername(),
                    "spring.datasource.password=" + postgreDBContainer.getPassword()
            );
        }
    }
}

