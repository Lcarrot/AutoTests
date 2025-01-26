package ru.itis.auto.test;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

@Owner(Developers.TEST_DEVELOPER)
public class SiteNavigationTest extends BaseTest {

    @BeforeAll
    public static void init() {
        driver.get(URL.buildUrl());
    }

    @Test
    @DisplayName("Успешно открывается версия для слабовидящих")
    @Description("Переход на версию для слабовидящих")
    public void isLinkForVisuallyImpairedWork() {
        clickOnElement(By.xpath("//div[@id='header']/div/div/a[2]/img"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("template=accessibility"));
    }

    @Test
    @DisplayName("Успешно открывается обычная версия")
    @Description("Переход на обычную версию")
    public void findAnyByWordInContentTest() {
        URL.addQueryParams("template",  "accessibility");
        driver.get(URL.buildUrl());
        clickOnElement(By.xpath("//div[@id='out']/div/a/img"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("template=shaper_express"));
    }

    @Test
    @DisplayName("Успешно открывается раздел сайта для студентов")
    @Description("Переход из меню в раздел сайта для студентов")
    public void findSitesForStudentJobs() {
        clickOnElement(By.xpath("//div[@id='hornav']/div/div/ul/li[4]/a/span/span"));
        Assertions.assertEquals(driver.getTitle(), "Студентам");
    }
}
