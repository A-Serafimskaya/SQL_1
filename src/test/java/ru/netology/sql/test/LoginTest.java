package ru.netology.sql.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.data.SQLHelper;
import ru.netology.sql.page.DashboardPage;
import ru.netology.sql.page.LoginPage;



public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
    }

    @AfterAll
    static void tearDownAll() {
        SQLHelper.deleteTestDataFromCardTransaction();
        SQLHelper.deleteTestDataFromCards();
        SQLHelper.deleteTestDataFromAuthCodes();
        SQLHelper.deleteTestDataFromUsers();
    }

    @Test
    void shouldSuccessLogin() {
        var info = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(info); // авторизуюсь и перехожу на страницу верификации
        var verificationCode = SQLHelper.getVerificationCode(); //получаю код верификации
       verificationPage.validVerify(verificationCode); // ввожу код верификации и перехожу на страницу дашборда
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.checkHeading();
    }

    @Test
    void shouldFailedLoginByInvalidPassword() {
        var info = DataHelper.getAuthInfo();
        loginPage.invalidLogin(info, "en"); // первый раз авторизуюсь с неверным паролем
        loginPage.invalidLogin(info, "en"); // второй раз авторизуюсь с неверным паролем
        loginPage.invalidLogin(info, "en"); // третий раз авторизуюсь с неверным паролем
        loginPage.checkErrorMessage("Система заблокирована");
    }
}

