package com.thesi.adapter;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
public class MySQLContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    static MySQLContainer<?> mySQLContainer =
      new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"));

    static {
        mySQLContainer.start();
        runMigrate(flyway(mySQLContainer));

    }

    public static Flyway flyway(MySQLContainer<?> mySQLContainer) {
        return Flyway.configure()
                 .dataSource(mySQLContainer.getJdbcUrl(), mySQLContainer.getUsername(), mySQLContainer.getPassword())
                 .locations("filesystem:db/migration")
                 .cleanDisabled(false)
                 .load();
    }

    public static void runMigrate(Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
          "spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
          "spring.datasource.username=" + mySQLContainer.getUsername(),
          "spring.datasource.password=" + mySQLContainer.getPassword()
        ).applyTo(applicationContext.getEnvironment());
    }
}
