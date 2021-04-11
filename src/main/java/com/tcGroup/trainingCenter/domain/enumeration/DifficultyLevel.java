package com.tcGroup.trainingCenter.domain.enumeration;

public enum DifficultyLevel {
    LOW("L"),
    MEDIUM("M"),
    HIGH("H");

    private final String abbreviation;

    DifficultyLevel(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static DifficultyLevel fromAbbreviation(String abbreviation) {
        if (abbreviation.equals(LOW.abbreviation)) {
            return LOW;
        } else if (abbreviation.equals(MEDIUM.abbreviation)) {
            return MEDIUM;
        } else if (abbreviation.equals(HIGH.abbreviation)) {
            return HIGH;
        } else {
            return null;
        }
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}