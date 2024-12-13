package com.mesmer.book_network.email;


import lombok.Getter;

@Getter
public enum EmailtemplateName {

    ACTIVATE_ACCOUNT("activate_account");

    private final String name;

    EmailtemplateName(String name) {
        this.name = name;
    }
}
