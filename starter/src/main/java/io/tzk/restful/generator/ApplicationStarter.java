package io.tzk.restful.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApplicationStarter {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ApplicationStarter.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(run::close));
    }
}
