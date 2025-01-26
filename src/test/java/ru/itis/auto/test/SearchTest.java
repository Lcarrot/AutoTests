package ru.itis.auto.test;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Owner(Developers.TEST_DEVELOPER)
public class SearchTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(SearchTest.class);

    @BeforeAll
    public static void init() {
        URL.addPath("using-joomla/extensions/components/search-component/search");
        driver.get(URL.buildUrl());
    }

    @Test
    @DisplayName("Успешный поиск по слову")
    @Description("Ищем материалы по слову  'качество'")
    public void findAnyByWordInContentTest() {
        String searchText = "качество";
        setValueToInput(By.id("search-searchword"), searchText);
        clickOnElement(By.id("searchphraseany"));
        clickOnElement(By.id("area-content"));
        clickOnElement(By.name("Search"));
        String value = getTextFromElement(By.xpath("//form[@id='searchForm']/div/p/strong"));
        int count = Integer.parseInt(value.split(" ")[3]);
        log.info("Total count {}", count);
        Assertions.assertTrue(count > 0);
    }

    @Test
    @DisplayName("Неуспешный поиск по слову")
    @Description("Ищем материалы по слову 'качампваа'")
    public void sfindNoneByWordInContentTest() {
        String searchText = "качампваа";
        setValueToInput(By.id("search-searchword"), searchText);
        clickOnElement(By.id("searchphraseany"));
        clickOnElement(By.id("area-content"));
        clickOnElement(By.name("Search"));
        String value = getTextFromElement(By.xpath("//form[@id='searchForm']/div/p/strong"));
        int count = Integer.parseInt(value.split(" ")[3]);
        log.info("Total count {}", count);
        Assertions.assertEquals(count, 0);
    }

    @Test
    @DisplayName("Успешный поиск по словосочетанию")
    @Description("Ищем материалы по слову  'качество'")
    public void findByAllWordsInContentTest() {
        String searchText = "качество образования";
        setValueToInput(By.id("search-searchword"), searchText);
        clickOnElement(By.id("searchphraseall-lbl"));
        clickOnElement(By.id("area-content"));
        clickOnElement(By.name("Search"));
        String value = getTextFromElement(By.xpath("//form[@id='searchForm']/div/p/strong"));
        int count = Integer.parseInt(value.split(" ")[3]);
        log.info("Total count {}", count);
        Assertions.assertTrue(count > 0);
    }

    @Test
    @DisplayName("Неуспешный поиск по словосочетанию")
    @Description("Ищем материалы по слову  'качество'")
    public void findNoneByAllWordsInContentTest() {
        String searchText = "качество обрезания";
        setValueToInput(By.id("search-searchword"), searchText);
        clickOnElement(By.id("searchphraseall-lbl"));
        clickOnElement(By.id("area-content"));
        clickOnElement(By.name("Search"));
        String value = getTextFromElement(By.xpath("//form[@id='searchForm']/div/p/strong"));
        int count = Integer.parseInt(value.split(" ")[3]);
        log.info("Total count {}", count);
        Assertions.assertEquals(count, 0);
    }
}
