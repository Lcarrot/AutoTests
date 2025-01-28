package ru.itis.auto.test.allure;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;
import ru.itis.auto.test.util.AppManager;

public class TestListener implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment("failed_screen", "image/png", "png",
                ((TakesScreenshot)AppManager.getDriver()).getScreenshotAs(OutputType.BYTES));
        Allure.addAttachment("failed_logs",
                String.valueOf(AppManager.getDriver().manage().logs().get(LogType.BROWSER).getAll()));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.getLifecycle().addAttachment("success_screen", "image/png", "png",
                ((TakesScreenshot)AppManager.getDriver()).getScreenshotAs(OutputType.BYTES));
        Allure.addAttachment("success_logs",
                String.valueOf(AppManager.getDriver().manage().logs().get(LogType.BROWSER).getAll()));
    }
}
