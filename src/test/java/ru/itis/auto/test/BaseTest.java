package ru.itis.auto.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.itis.auto.test.allure.TestListener;
import ru.itis.auto.test.dto.UrlBuilder;

import java.time.Duration;

@ExtendWith(TestListener.class)
public abstract class BaseTest {
    protected static WebDriver driver;
    protected static JavascriptExecutor js;
    protected static final UrlBuilder URL = new UrlBuilder("http://sbk12.ru/");

    @BeforeAll
    public static void startTests() {
        AppManager.initDriver();
        driver = AppManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        Dimension dimension = new Dimension(1920, 1080);
        driver.manage().window().setSize(dimension);
        js = (JavascriptExecutor) driver;
    }

    @AfterAll
    public static void close() {
        AppManager.onDestroy();
        URL.clearPath();
    }

    protected void setValueToInput(By path, String value) {
        clickOnElement(path);
        driver.findElement(path).clear();
        driver.findElement(path).sendKeys(value);
    }

    protected String closeAlertAndGetItsText(boolean acceptNextAlert) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (acceptNextAlert) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return alertText;
    }

    protected void clickOnElement(By path) {
        driver.findElement(path).click();
    }

    protected String getTextFromElement(By path) {
        return driver.findElement(path).getText();
    }
}
