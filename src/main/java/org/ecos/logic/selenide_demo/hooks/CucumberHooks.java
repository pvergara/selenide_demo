package org.ecos.logic.selenide_demo.hooks;

import org.ecos.logic.selenide_demo.init.WebDriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@SuppressWarnings("unused")
public class CucumberHooks {

    private static final Logger sLog = LoggerFactory.getLogger(CucumberHooks.class);


    @BeforeAll
    public static void CucumberSetUpSuite() {
        sLog.info("Cucumber Suite SetUp");
    }

    @Before
    public void beforeEachScenario(Scenario scenario) {
        WebDriverFactory.getWebDriverInstance();
    }

    @After
    public void logoutAndCLeanData(Scenario scenario) {

        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) getWebDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        WebDriverFactory.closeWebDriverInstance();
    }

    @AfterAll
    public static void CucumberTearDownSuite() {
        sLog.info("Cucumber Suite Tear Down");
    }

}