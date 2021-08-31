package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    void acceptingGeneratedInformation() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(DataGenerator.getNewCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String DateFirst = DataGenerator.generateDate(3);
        $("[data-test-id='date'] input").setValue(DateFirst);
        $("[data-test-id='name'] input").setValue(DataGenerator.getNewName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.getNewPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String DateSecond = DataGenerator.generateDate(5);
        $("[data-test-id='date'] input").setValue(DateSecond);
        $(By.className("button")).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(visible);
    }

}
