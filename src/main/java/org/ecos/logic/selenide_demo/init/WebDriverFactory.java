package org.ecos.logic.selenide_demo.utils.init;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.ecos.logic.selenide_demo.utils.PropertyUtils;

import static com.codeborne.selenide.Browsers.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/*
 * Selenide creates new WebDriver instance for each thread using ThreadLocal. But the Configuration properties are
 * shared among threads
 */
public class WebDriverFactory {

    public static void getWebDriverInstance() {
        createWebDriver();
        open();
        getWebDriver().manage().window().maximize();

    }

    public static void closeWebDriverInstance() {
        shutdownWebDriver();
    }

    private static void createWebDriver() {
        String browser = PropertyUtils.getPropString("selenide.browser", "selenide.properties");
        Boolean headless = PropertyUtils.getPropBoolean("selenide.headless", "selenide.properties");
        Integer timeout = PropertyUtils.getPropInteger("selenide.timeout", "selenide.properties");
        Boolean remoteExecution = PropertyUtils.getPropBoolean("selenide.remote.execution", "selenide.properties");
        String remoteHost = PropertyUtils.getPropString("selenide.remote", "selenide.properties");

        if (remoteExecution) {
            Configuration.remote = remoteHost;
        }

        switch (browser) {
            case IE:
                Configuration.fastSetValue = true;
                break;
            case CHROME:
                Configuration.headless = headless;
                break;
            case FIREFOX:
            case EDGE:
                break;
            default:
                System.out.println("No previously known web browser is specified,"
                        + " please check properties file for any missmatch. Specified browser is: " + browser);
                break;
        }
        Configuration.browser = browser;
        Configuration.timeout = timeout;


    }

    private static void shutdownWebDriver() {
        WebDriverRunner.closeWebDriver();
    }

}