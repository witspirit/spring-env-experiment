package be.witspirit.experiment.spring.env.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(EnvironmentSetup.class)
public class AppConfig {
}
