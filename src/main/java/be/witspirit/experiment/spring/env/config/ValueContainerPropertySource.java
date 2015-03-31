package be.witspirit.experiment.spring.env.config;

import org.springframework.core.env.PropertySource;

public class ValueContainerPropertySource extends PropertySource {

    private String triggerProperty;
    private ValueContainer valueContainer;

    public ValueContainerPropertySource(String triggerProperty, ValueContainer valueContainer) {
        super("ValueContainerPropertySource");
        this.triggerProperty = triggerProperty;
        this.valueContainer = valueContainer;
    }

    @Override
    public Object getProperty(String name) {
        if (name.equals(triggerProperty)) {
            return valueContainer.getValue();
        }
        return null;
    }
}
