package org.ecos.logic.selenide_demo.utils.config;

import org.springframework.boot.test.context.SpringBootTest;


import io.cucumber.spring.CucumberContextConfiguration;

@SuppressWarnings("unused")
@CucumberContextConfiguration
@SpringBootTest(classes = SpringConfiguration.class)
public class CucumberSpringConfiguration {
}