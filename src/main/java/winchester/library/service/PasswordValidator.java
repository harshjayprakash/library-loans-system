package winchester.library.service;

/**
 * A class that checks password strength.
 */
public final class PasswordValidator {

    public final String passwordRequirements;
    private final int passwordLength;

    public PasswordValidator() {
        this.passwordLength = 8;
        this.passwordRequirements = String.format(
                """
                Your password must:
                - be at least %d characters long
                - contain a number (0-9)
                - contain a letter (A-Z, a-z)
                - contain a special character (. , : @ # £ $ & * ! ? < > SPACE)
                """, this.passwordLength);
    }

    public String getPasswordRequirements() {
        return this.passwordRequirements;
    }

    public boolean meetsAllRequirements(String password) {
        return meetsLengthRequirement(password) && meetsNumberRequirement(password)
                && meetsLetterRequirement(password) && meetsSpecialCharacterRequirements(password);
    }

    public boolean meetsLengthRequirement(String password) {
        return password.length() >= this.passwordLength;
    }

    public boolean meetsNumberRequirement(String password) {
        for (int i = 0; i < 10; i++) {
            if (password.contains(String.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean meetsLetterRequirement(String password) {
        for (char character : password.toCharArray()) {
            if (Character.isLetter(character)) {
                return true;
            }
        }
        return false;
    }

    public boolean meetsSpecialCharacterRequirements(String password) {
        for (char character : password.toCharArray()) {
            if (switch (character) {
                case '.', ',', ':', '@', '#', '£', '$', '&', '*', '!', '?', '<', '>', ' ' -> true;
                default -> false;
            }) {
                return true;
            }
        }
        return false;
    }
}
