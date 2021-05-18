package com.tcGroup.trainingCenter.domain.enumeration;

public enum DifficultyLevel {
    LOW(1, "L"),
    MEDIUM(2, "M"),
    HIGH(3, "H");

    private final double level;
    private final String abbreviation;

    DifficultyLevel(double level, String abbreviation) {
        this.level = level;
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

    public double getLevel() {
        return level;
    }
}