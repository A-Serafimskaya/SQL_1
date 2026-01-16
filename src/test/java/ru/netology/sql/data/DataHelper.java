package ru.netology.sql.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    private static Faker faker;

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static String generateInvalidPassword(String locale) {
        faker = new Faker(new Locale(locale));

        return faker.internet().password();
    }
}

