package be.witspirit.experiment.spring.env;

import be.witspirit.experiment.spring.env.config.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(classes = AppConfig.class)
public class EnvironmentTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private Environment env;

    @Value("${simpleProperty}")
    private String simpleProperty;

    @Value("${overridenProperty}")
    private String overridenProperty;

    @Value("${envTarget}")
    private String envTarget;

    @Value("${tst.prefixedProperty}")
    private String tstPrefixedProperty;

    @Value("${${envTarget}.prefixedProperty}")
    private String envPrefixedProperty;

    @Value("${prefixedProperty}")
    private String prefixedProperty;

    @Value("${vcProperty}")
    private String vcProperty;

    @Test
    public void propertiesFromEnvironment() {
        Assert.assertEquals("simplePropertyVal", env.getProperty("simpleProperty"));
        Assert.assertEquals("fromOverride", env.getProperty("overridenProperty"));

        Assert.assertEquals("tst", env.getProperty("envTarget"));
        Assert.assertEquals("tstOverride", env.getProperty("tst.prefixedProperty"));
        Assert.assertEquals("tstOverride", env.getProperty(env.getProperty("envTarget")+".prefixedProperty"));

        Assert.assertEquals("tstOverride", env.getProperty("prefixedProperty"));

        Assert.assertEquals("tstOverrideVcValue", env.getProperty("vcProperty"));
    }

    @Test
    public void propertiesFromPlaceholders() {
        Assert.assertEquals("simplePropertyVal", simpleProperty);
        Assert.assertEquals("fromOverride", overridenProperty);

        Assert.assertEquals("tst", envTarget);
        Assert.assertEquals("tstOverride", tstPrefixedProperty);
        Assert.assertEquals("tstOverride", envPrefixedProperty);

        Assert.assertEquals("tstOverride", prefixedProperty);

        Assert.assertEquals("tstOverrideVcValue", vcProperty);
    }
}
