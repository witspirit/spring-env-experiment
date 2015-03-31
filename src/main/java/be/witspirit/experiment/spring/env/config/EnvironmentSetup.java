package be.witspirit.experiment.spring.env.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@PropertySource({"common.properties", "override.properties"})
public class EnvironmentSetup {

    @Autowired
    private ConfigurableEnvironment env;

    @Bean
    public PrefixResolvedPropertySource prefixResolver() {
        PrefixResolvedPropertySource prefixSource = new PrefixResolvedPropertySource("envTarget", env);
        env.getPropertySources().addFirst(prefixSource);
        return prefixSource;
    }

    @Bean
    public ValueContainerPropertySource valueContainerPropertySource() {
        ValueContainerPropertySource source = new ValueContainerPropertySource("vcProperty", valueContainer());
        env.getPropertySources().addLast(source);
        return source;
    }

    @Bean
    public ValueContainer valueContainer() {
        return new ValueContainer("DependentValue");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
