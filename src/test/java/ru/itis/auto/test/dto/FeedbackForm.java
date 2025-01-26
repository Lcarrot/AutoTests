package ru.itis.auto.test.dto;

public record FeedbackForm(String name, String email, String phone, String city, String comment, long captcha) {
}
