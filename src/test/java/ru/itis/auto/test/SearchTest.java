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
    @DisplayName("�������� ����� �� �����")
    @Description("���� ��������� �� �����  '��������'")
    public void findAnyByWordInContentTest() {
        String searchText = "��������";
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
    @DisplayName("���������� ����� �� �����")
    @Description("���� ��������� �� ����� '���������'")
    public void sfindNoneByWordInContentTest() {
        String searchText = "���������";
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
    @DisplayName("�������� ����� �� ��������������")
    @Description("���� ��������� �� �����  '��������'")
    public void findByAllWordsInContentTest() {
        String searchText = "�������� �����������";
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
    @DisplayName("���������� ����� �� ��������������")
    @Description("���� ��������� �� �����  '��������'")
    public void findNoneByAllWordsInContentTest() {
        String searchText = "�������� ���������";
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
