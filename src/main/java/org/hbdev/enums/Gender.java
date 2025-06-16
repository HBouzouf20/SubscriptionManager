package org.hbdev.enums;

public enum Gender {
    MALE, FEMALE;

    public static Gender fromString(String gender) {
        return Gender.valueOf(gender.toUpperCase());
    }
    public String toString() {
        return this.name().toLowerCase();
    }
}
