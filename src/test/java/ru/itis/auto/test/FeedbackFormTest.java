package ru.itis.auto.test;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import ru.itis.auto.test.dto.FeedbackForm;
import ru.itis.auto.test.util.Developers;

@Owner(Developers.TEST_DEVELOPER)
public class FeedbackFormTest extends BaseTest {

    @BeforeAll
    public static void init() {
        URL.addPath("podat-zayavku");
        driver.get(URL.buildUrl());
    }

    @Test
    @DisplayName("Ошибка капчи")
    @Description("Все поля введены верно, кроме капчи")
    public void captchaErrorTest() {
        FeedbackForm form = new FeedbackForm("Иван", "zugnomemla@tozya.com", "+447700184665",
                "Казань", "хочу поступить", 111);
        setValueToInput(By.id("sf2_157_vashe_imja"), form.name());
        setValueToInput(By.id("sf2_157_e-mail"), form.email());
        setValueToInput(By.id("sf2_157_telefon"), form.phone());
        setValueToInput(By.id("sf2_157_gorod_strana"),form.city());
        setValueToInput(By.id("sf2_157_vash_kommentarijj"), form.comment());
        setValueToInput(By.id("sf2_157_proverochnyjj_kod"), String.valueOf(form.captcha()));

        clickOnElement(By.xpath("//input[@value='Отправить']"));
        Assertions.assertEquals(closeAlertAndGetItsText(true), "Не верно заполнено поле Проверочный код.");
    }

    @Test
    @DisplayName("Ошибка email")
    @Description("Все поля введены верно, кроме email и капчи")
    public void incorrectEmailTest() {
        driver.get(URL.buildUrl());

        FeedbackForm form = new FeedbackForm("Иван", "zugnomemladsad@tdfs", "+447700184665",
                "Казань", "хочу поступить", 111);
        setValueToInput(By.id("sf2_157_vashe_imja"), form.name());
        setValueToInput(By.id("sf2_157_e-mail"), form.email());
        setValueToInput(By.id("sf2_157_telefon"), form.phone());
        setValueToInput(By.id("sf2_157_gorod_strana"),form.city());
        setValueToInput(By.id("sf2_157_vash_kommentarijj"), form.comment());
        setValueToInput(By.id("sf2_157_proverochnyjj_kod"), String.valueOf(form.captcha()));

        clickOnElement(By.xpath("//input[@value='Отправить']"));
        Assertions.assertEquals(closeAlertAndGetItsText(true), "Введите E-mail");
    }

    @Test
    @DisplayName("Ошибка email")
    @Description("Все поля введены верно, кроме телефона и капчи")
    public void incorrectPhoneTest() {
        driver.get(URL.buildUrl());

        FeedbackForm form = new FeedbackForm("Иван", "zugnomemladsad@tdfs.ru", "abcdefabc",
                "Казань", "хочу поступить", 111);
        setValueToInput(By.id("sf2_157_vashe_imja"), form.name());
        setValueToInput(By.id("sf2_157_e-mail"), form.email());
        setValueToInput(By.id("sf2_157_telefon"), form.phone());
        setValueToInput(By.id("sf2_157_gorod_strana"),form.city());
        setValueToInput(By.id("sf2_157_vash_kommentarijj"), form.comment());
        setValueToInput(By.id("sf2_157_proverochnyjj_kod"), String.valueOf(form.captcha()));

        clickOnElement(By.xpath("//input[@value='Отправить']"));
        Assertions.assertEquals(closeAlertAndGetItsText(true), "Введите Номер телефона");
    }
}
