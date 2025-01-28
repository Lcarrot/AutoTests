package ru.itis.auto.test.util;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.Assert.fail;

public class AppManager {

    private static WebDriver webDriver = null;
    protected static StringBuffer verificationErrors = new StringBuffer();

    public static void initDriver() {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        ChromeOptions options = new ChromeOptions();
        options.merge(dc);
        webDriver = new ChromeDriver(options);
    }

    public static WebDriver getDriver() {
        return webDriver;
    }

    public static void onDestroy() {
        webDriver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!verificationErrorString.isEmpty()) {
            fail(verificationErrorString);
        }
    }
}
