package winchester.library.service;

import java.util.Optional;

public enum PasswordConstants {
    REQUIREMENTS_MET(0, "Password requirements have been met"),
    NOT_LONG_ENOUGH(1, "not long enough"),
    DOES_NOT_CONTAIN_NUMBER(2, "does not contain a number"),
    DOES_NOT_CONTAIN_LETTER(3, "does not contain a letter"),
    DOES_NOT_CONTAIN_SPECIAL_CHARACTER(4, "does not contain a special character"),
    DOES_NOT_MATCH(5, "does not match"),
    DOES_NOT_MEET_ANY_REQUIREMENTS(15, "does not meet any of the requirements");


    private final int identifier;
    private final String message;

    PasswordConstants(int identifier, String message) {
        this.identifier = identifier;
        this.message = message;
    }

    public static Optional<PasswordConstants> fromIdentifier(int identifier) {
        for (PasswordConstants constant : PasswordConstants.values()) {
            if (constant.identifier == identifier) {
                return Optional.of(constant);
            }
        }
        return Optional.empty();
    }

    public int getIdentifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
