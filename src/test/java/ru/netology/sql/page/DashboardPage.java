package ru.netology.sql.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    public DashboardPage() {
        heading.shouldBe(visible);
    }
    private final SelenideElement heading = $("[data-test-id=dashboard]");

    public void checkHeading() {
        heading.shouldBe(visible, text("Личный кабинет"));
    }


}