package com.tcGroup.trainingCenter.user.enumeration;

public enum AccountStatus {
    ACTIVE("A"),
    INACTIVE("I");

    private final String abbreviation;

    private AccountStatus(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static AccountStatus fromAbbreviation(String abbreviation) {
        if (abbreviation.equals(ACTIVE.abbreviation)) {
            return ACTIVE;
        } else if (abbreviation.equals(INACTIVE.abbreviation)) {
            return INACTIVE;
        } else {
            return null;
        }
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}