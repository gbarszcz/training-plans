package com.tcGroup.trainingCenter.account;

public enum AccountStatus {
    ACTIVE("A"),
    INACTIVE("I");

    private String abbreviation;

    AccountStatus(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
