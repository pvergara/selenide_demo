package org.ecos.logic.selenide_demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan({ "org.ecos.logic.selenide_demo" })
@PropertySources({ @PropertySource("classpath:application.properties"), })
public class SpringConfiguration {
}