package be.witspirit.experiment.spring.env.config;


import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

public class PrefixResolvedPropertySource extends PropertySource {

    private String prefixSelector;
    private Environment env;

    public PrefixResolvedPropertySource(String prefixSelector, Environment env) {
        super("PrefixResolvedPropertySource");
        this.prefixSelector = prefixSelector;
        this.env = env;
    }

    @Override
    public Object getProperty(String name) {
        // Don't resolve my own requests...
        if (name.equals(prefixSelector)) {
            return null;
        }

        String prefix = env.getProperty(prefixSelector);
        // Don't resolve my own requests...
        if (name.startsWith(prefix)) {
            return null;
        }

        // If the prefixed property does not exist, we don't have to fallback. The environment will look in the remainder sources anyhow.
        return env.getProperty(prefix + "." + name);
    }
}
