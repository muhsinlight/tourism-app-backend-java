package com.teknofest.turizm.request;

public record RegisterRequest(String firstName, String lastName, String email, String password) {
}