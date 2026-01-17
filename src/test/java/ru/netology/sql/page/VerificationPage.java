package ru.netology.sql.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement verifyCode = $("[data-test-id=code] input");
    private final SelenideElement nextVerifyButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorMessage = $(".notification__content");

    public void verifyIsVerificationPage() {
        verifyCode.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void validVerify(String verifyCodeValue) {
        verifyCode.setValue(verifyCodeValue);
        nextVerifyButton.click();
    }

    public void ifCodeIsInvalid() {
        errorMessage.shouldHave(visible, text("Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }
}