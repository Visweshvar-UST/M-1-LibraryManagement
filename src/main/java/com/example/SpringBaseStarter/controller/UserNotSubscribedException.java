package com.example.SpringBaseStarter.controller;

public class UserNotSubscribedException extends RuntimeException {
    public UserNotSubscribedException(String userSubscriptionHasExpired) {
        super(userSubscriptionHasExpired);
    }
}
