package com.softgen.softlab.ecommercebackend.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserNotVerifiedException extends Exception{
    private final boolean newEmailSent;
    public boolean isNewEmailSent() {
        return newEmailSent;
    }
}
